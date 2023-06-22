package property.application.service;

import property.application.dto.request.OfferDto;
import property.application.dto.response.OfferResponseDto;
import property.application.model.Offer;

import java.util.List;

public interface OfferService {


    // make offer to property
    OfferDto makeOffer(OfferDto offerDto);

    OfferResponseDto acceptOffer(Long id);

    OfferResponseDto rejectOffer(Long id);


   List<OfferResponseDto> getAllOffersByPropertyId(Long id);

        void deleteOffer(Long id);

}
