package property.application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PropertyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;
    private int bedrooms;
    private int bathrooms;
    private int lotSize;
    private Double rentAmount;
    private Double securityDepositAmount;
    private int yearBuilt;






}
