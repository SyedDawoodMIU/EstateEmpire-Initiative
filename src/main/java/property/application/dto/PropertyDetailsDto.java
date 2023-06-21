package property.application.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import property.application.model.Property;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
