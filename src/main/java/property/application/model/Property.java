package property.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import property.application.model.enums.PropertyType;

import java.util.List;

@Entity
@Data
public class Property extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    private int viewCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private PropertyDetails propertyDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "property")
    @JsonBackReference
    private List<PropertyImage> propertyImage;

}
