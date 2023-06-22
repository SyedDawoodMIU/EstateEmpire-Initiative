        package property.application.service.impl;

        import org.modelmapper.ModelMapper;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import property.application.dto.request.OfferDto;
        import property.application.dto.response.OfferResponseDto;
        import property.application.model.Offer;
        import property.application.model.Property;
        import property.application.model.User;
        import property.application.model.enums.OfferStatus;
        import property.application.model.enums.PropertyStatus;
        import property.application.repo.OfferRepository;
        import property.application.repo.PropertyRepository;
        import property.application.repo.UserRepo;
        import property.application.service.OfferService;
        import property.application.util.LoggedinUserUtil;
        import java.util.List;

        @Service
        public class OfferServiceImpl implements OfferService {
            @Autowired
            private OfferRepository offerRepository;
            @Autowired
            private UserRepo userRepo;
            @Autowired
            private PropertyRepository propertyRepository;
            @Autowired
            ModelMapper modelMapper;
            @Autowired
            LoggedinUserUtil loggedinUserUtil;
            @Override
            public OfferDto makeOffer(OfferDto offerDto){
                Property property = propertyRepository.findById(offerDto.getPropertyId()).orElseThrow(() -> new RuntimeException("User not found"));
                User user = userRepo.findById(offerDto.getCustomerId()).orElseThrow(() -> new RuntimeException("User not found"));
                if(property != null && property.getStatus().equals("AVAILABLE") && user != null){

                    Offer offer = modelMapper.map(offerDto, Offer.class);
                    offer.setOfferStatus(OfferStatus.PENDING);
                    offer.setProperty(property);
                    property.setStatus(PropertyStatus.PENDING);
                    offerRepository.save(offer);
                    return modelMapper.map(offer, OfferDto.class);
                }
        return null;
            }
            @Override

            public OfferResponseDto acceptOffer(Long id){
                Offer offer = offerRepository.findById(id).orElseThrow(() -> new RuntimeException("Offer not found"));
                User user = userRepo.findById(offer.getId()).orElseThrow(() -> new RuntimeException("User not found"));
                if(user != null){
                    offer.setOfferStatus(OfferStatus.ACCEPTED);
                    offerRepository.save(offer);
                    Property property = offer.getProperty();
                    property.setStatus(PropertyStatus.CONTINGENT);
                    propertyRepository.save(property);
                    return modelMapper.map(offer, OfferResponseDto.class);
                }
                return null;
            }

            @Override
            public OfferResponseDto rejectOffer(Long id){
                Offer offer = offerRepository.findById(id).orElseThrow(() -> new RuntimeException("Offer not found"));
                User user = userRepo.findById(offer.getId()).orElseThrow(() -> new RuntimeException("User not found"));
                if(user == null){
                    Property property = offer.getProperty();
                    if(property.getStatus().equals(PropertyStatus.CONTINGENT)){
                     throw new RuntimeException("Property is already contingent");
                    }
                    offer.setOfferStatus(OfferStatus.REJECTED);
                    property.setStatus(PropertyStatus.AVAILABLE);
                    offerRepository.save(offer);
                    return modelMapper.map(offer, OfferResponseDto.class);
                }
                return null;
            }

            @Override

            public List<OfferResponseDto> getAllOffersByPropertyId(Long Id){
                List<Offer> offers = offerRepository.findAllByPropertyPropertyId(Id);
                return offers.stream().map(offer -> modelMapper.map(offer, OfferResponseDto.class)).toList();
            }

            @Override
               public void deleteOffer(Long offerId){
               User user = loggedinUserUtil.getCurrentUser();
                Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new RuntimeException("Offer not found"));
                Property property = offer.getProperty();
                if(offer.getProperty().getOwner().equals(user)) {
                   offerRepository.delete(offer);
                }
                if(property.getStatus().equals(PropertyStatus.CONTINGENT)){
                    throw new RuntimeException("Property can not be deleted");
                }
                else {
                    offerRepository.delete(offer);
                }
            }
        }
