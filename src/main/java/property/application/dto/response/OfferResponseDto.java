package property.application.dto.response;

import lombok.Data;
@Data
public class OfferResponseDto {

    private Double offerPrice;
    private PropertyDto propertyId;
    private UserDtoResponse customerId;
}
