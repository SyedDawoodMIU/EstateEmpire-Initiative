package property.application.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import property.application.dto.response.OfferResponseDto;
import property.application.model.Property;
import property.application.service.OfferService;
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
    public ResponseEntity<byte[]> downloadReceipt(@PathVariable Long customerId, @PathVariable Long offerId) {
        byte[] outputStream = offerService.downloadReceipt(customerId, offerId);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "receipt.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions that occur during PDF generation
            return ResponseEntity.status(500).build();
        }
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
