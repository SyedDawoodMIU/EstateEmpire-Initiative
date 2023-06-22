package property.application.dto.response;

import lombok.Data;
@Data
public class OfferResponseDto {

    private Long id;
    private Double offerPrice;
    private PropertyDto property;
    private UserDtoResponse customer;
}
