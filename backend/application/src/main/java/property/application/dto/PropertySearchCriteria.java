package property.application.dto;

import lombok.Data;
import property.application.model.enums.PropertyType;

@Data
public class PropertySearchCriteria {

    private String city;
    private String state;
    private String zipCode;
    private String country;
    private PropertyType type;
    private int bedrooms;
    private int bathrooms;
    private int lotSize;
    private Double rentAmount;
    private int yearBuilt;

}
