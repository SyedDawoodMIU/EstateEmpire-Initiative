package property.application.dto;

import lombok.Data;
import property.application.model.Property;

@Data
public class PropertyDetailsDto {
    private Property property;
    private int bedrooms;
    private int bathrooms;
    private int lotSize;
    private Double rentAmount;
    private Double securityDepositAmount;
    private int yearBuilt;
    private String description;
}
