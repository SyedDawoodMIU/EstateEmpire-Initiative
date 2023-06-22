package property.application.service;

import property.application.dto.request.OfferDto;
import property.application.dto.response.OfferResponseDto;
import property.application.model.Offer;
import property.application.model.Property;

import java.util.List;

public interface OfferService {

    OfferDto makeOffer(OfferDto offerDto);

    OfferResponseDto acceptOffer(Long id);

    OfferResponseDto rejectOffer(Long id);

    List<OfferResponseDto> getAllOffersByPropertyId(Long Id);

    void deleteOffer(Long offerId);

    List<OfferResponseDto> getOfferHistoryByUserId(Long customerId);

    void downloadReceipt(Long customerId, Long offerId);

    void saveProperty(Long customerId, Long propertyId);

    List<Property> getSavedProperties(Long customerId);

}
