package property.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import property.application.model.enums.OfferStatus;
import property.application.model.enums.PropertyType;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
