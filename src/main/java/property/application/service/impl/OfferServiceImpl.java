package property.application.service.impl;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import property.application.controller.constants.BaseErrorCode;
import property.application.dto.request.OfferDto;
import property.application.dto.response.OfferResponseDto;
import property.application.exception.BadRequestException;
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
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final LoggedinUserUtil loggedinUserUtil;

    private final ModelMapper modelMapper;

    private final OfferRepository offerRepository;

    private final UserRepo userRepo;

    private final PropertyRepository propertyRepository;

    @Override
    public OfferDto makeOffer(OfferDto offerDto) {
        Property property = propertyRepository.findById(offerDto.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));
        if (property.getStatus().equals(PropertyStatus.AVAILABLE)) {
            Offer offer = Offer.builder()
                    .offerStatus(OfferStatus.PENDING)
                    .property(property)
                    .customer(loggedinUserUtil.getCurrentUser())
                    .offerPrice(offerDto.getOfferPrice())
                    .build();
            offerRepository.save(offer);
            return modelMapper.map(offer, OfferDto.class);
        }
        return null;
    }

    @Override

    public OfferResponseDto acceptOffer(Long id) {
        User user = loggedinUserUtil.getCurrentUser();
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Offer not found"));
        Property property = offer.getProperty();
        if (!property.getOwner().equals(user)) {
            throw new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Only owner can accept an offer");
        }
        offer.setOfferStatus(OfferStatus.ACCEPTED);
        offerRepository.save(offer);
        property.setStatus(PropertyStatus.PENDING);
        propertyRepository.save(property);
        return modelMapper.map(offer, OfferResponseDto.class);
    }

    @Override
    public OfferResponseDto rejectOffer(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Offer not found"));
        Property property = offer.getProperty();
        if (!offer.getProperty().getOwner().equals(loggedinUserUtil.getCurrentUser())) {
            throw new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Only owner can accept an offer");
        }
        if (property.getStatus().equals(PropertyStatus.CONTINGENT)) {
            throw new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Property is already contingent");
        }
        offer.setOfferStatus(OfferStatus.REJECTED);
        property.setStatus(PropertyStatus.AVAILABLE);
        offerRepository.save(offer);
        return modelMapper.map(offer, OfferResponseDto.class);
    }

    @Override
    public List<OfferResponseDto> getAllOffersByPropertyId(Long Id) {
        List<Offer> offers = offerRepository.findAllByPropertyPropertyId(Id);
        return offers.stream().map(offer -> modelMapper.map(offer, OfferResponseDto.class)).toList();
    }

    @Override
    public void deleteOffer(Long offerId) {
        User user = loggedinUserUtil.getCurrentUser();
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Offer not found"));
        Property property = offer.getProperty();
        if (offer.getProperty().getOwner().getUserId() == user.getUserId()) {
            offerRepository.delete(offer);
        }
        if (property.getStatus().equals(PropertyStatus.CONTINGENT) ||
                property.getStatus().equals(PropertyStatus.PENDING)) {
            throw new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Property can not be deleted");
        } else {
            offerRepository.delete(offer);
        }
    }

    @Autowired
    private UserRepo customerRepository;

    @Override
    public List<OfferResponseDto> getOfferHistoryByUserId(Long customerId) {
        User customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Offer> offers = offerRepository.findAllByCustomer(customer);
        return offers.stream()
                .map(offer -> modelMapper.map(offer, OfferResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public byte[] downloadReceipt(Long customerId, Long offerId) {
        User customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Create a new PDF document and write the receipt content
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Receipt Details:");
            contentStream.newLine();
            contentStream.showText("Customer Name: " + customer.getName());
            contentStream.newLine();
            contentStream.showText("Offer Price: " + offer.getOfferPrice());
            contentStream.endText();
            contentStream.close();
            document.save(outputStream);
            document.close();

            // Set the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "receipt.pdf");

            // Return the PDF file as a byte array
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }

    }

    @Override
    public void saveProperty(Long customerId, Long propertyId) {
        User customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        customer.getFavoriteProperty().add(property);
        customerRepository.save(customer);
    }

    @Override
    public List<Property> getSavedProperties(Long customerId) {
        User customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return customer.getFavoriteProperty();
    }

}
