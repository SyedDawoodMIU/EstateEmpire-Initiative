package property.application.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Property extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;
    private String type;
    private int viewCount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private PropertyDetails propertyDetails;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;



}
