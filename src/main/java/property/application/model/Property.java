package property.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import property.application.model.enums.PropertyType;
import property.application.model.enums.PropStatus;
import property.application.model.enums.PropertyStatus;

import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Property extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    @Enumerated(EnumType.STRING)
    private PropertyType type;
    @Enumerated(EnumType.STRING)
    private PropertyStatus status;



    private int viewCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private PropertyDetails propertyDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "property")
    @JsonBackReference
    private List<PropertyImage> propertyImage;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Transient
    private Boolean isFavorite;

    @ManyToMany(mappedBy = "favoriteProperty")
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property)) return false;
        Property property = (Property) o;
        return propertyId != null && property.propertyId != null && propertyId.equals(property.propertyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyId);
    }



}
