package property.application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PropertyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int bedrooms;
    private int bathrooms;
    private int lotSize;
    private Double rentAmount;
    private Double securityDepositAmount;
    private int yearBuilt;
    @Column(length = 1000)
    private String description;

}
