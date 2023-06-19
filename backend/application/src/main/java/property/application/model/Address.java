package property.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Address {
        @Id
        @GeneratedValue
        private Long addressId;
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private String country;
        @OneToOne(mappedBy = "address")
        private PropertyDetails propertyDetails;

}
