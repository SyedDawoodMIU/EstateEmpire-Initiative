package property.application.model;

import jakarta.persistence.*;
import lombok.Data;
import property.application.model.enums.OfferStatus;
import property.application.model.enums.PropertyType;
@Entity
@Data

public class Offer extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double offerPrice;
    @Enumerated
    private OfferStatus offerStatus;
    @ManyToOne
    private Property property;
    @ManyToOne
    private User customer;


}
