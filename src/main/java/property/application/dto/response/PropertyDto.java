package property.application.dto.response;

import lombok.Data;
import property.application.dto.AddressDto;
import property.application.dto.PropertyImageDto;
import property.application.model.PropertyDetails;
import property.application.model.enums.PropertyStatus;
import property.application.model.enums.PropertyType;

import java.util.List;

@Data
public class PropertyDto {
    private Long propertyId;
    private PropertyType type;
    private int viewCount;
    private AddressDto address;
    private PropertyDetails propertyDetails;
    private PropertyStatus status;
    private List<PropertyImageDto> propertyImage;
    private Boolean isFavorite;
    private Boolean isOwner;
}
