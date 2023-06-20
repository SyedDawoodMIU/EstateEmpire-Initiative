package property.application.dto;

import lombok.Data;
import property.application.model.Address;
import property.application.model.PropertyDetails;

@Data
public class PropertyDto {
        private Long propertyId;
        private String type;
        private int viewCount;
        private AddressDto address;
        private PropertyDetails propertyDetails;
    }


