package property.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import property.application.dto.request.OfferDto;
import property.application.dto.response.OfferResponseDto;
import property.application.model.Offer;
import property.application.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping
        public OfferDto save(@RequestBody OfferDto offerDto) {
         return offerService.makeOffer(offerDto);
    }
    @PostMapping("/accept")
    public OfferResponseDto acceptOffer(@RequestBody Long id){
        return offerService.acceptOffer(id);
    }
    @PostMapping("/reject")
    public OfferResponseDto rejectOffer(@RequestBody Long id){
        return offerService.rejectOffer(id);
    }

    @GetMapping("/{propertyId}")
    public List<OfferResponseDto> getAllOffers(@PathVariable Long propertyId){
        return offerService.getAllOffersByPropertyId(propertyId);
    }
    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id){
        offerService.deleteOffer(id);
    }



}
