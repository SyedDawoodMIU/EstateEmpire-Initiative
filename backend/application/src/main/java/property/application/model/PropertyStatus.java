package property.application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PropertyStatus extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private boolean isAvailable;
   @OneToOne
    @JoinColumn(name = "property_id")
     private Property property;

}
