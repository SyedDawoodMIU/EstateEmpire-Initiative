package property.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import property.application.dto.request.OfferDto;
import property.application.dto.request.UserDto;
import property.application.dto.response.LoginResponse;
import property.application.dto.response.OfferResponseDto;
import property.application.dto.response.UserDtoResponse;
import property.application.model.Property;
import property.application.service.OfferService;
import property.application.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private OfferService offerService;

    @GetMapping("/{customerId}/offer-history")
    public List<OfferResponseDto> getOfferHistoryByCustomerId(@PathVariable Long customerId) {
        return offerService.getOfferHistoryByUserId(customerId);
    }

    @GetMapping("/{customerId}/current-offers")
    public List<OfferResponseDto> getCurrentOffersByCustomerId(@PathVariable Long customerId) {
        return offerService.getOfferHistoryByUserId(customerId);
    }
   

    @GetMapping("/{customerId}/receipt/{offerId}")
    public void downloadReceipt(@PathVariable Long customerId, @PathVariable Long offerId) {
        offerService.downloadReceipt(customerId, offerId);
    }

    @PostMapping("/{customerId}/save/{propertyId}")
    public void saveProperty(@PathVariable Long customerId, @PathVariable Long propertyId) {
        offerService.saveProperty(customerId, propertyId);
    }

    @GetMapping("/{customerId}/saved-properties")
    public List<Property> getSavedProperties(@PathVariable Long customerId) {
        return offerService.getSavedProperties(customerId);
    }
}
