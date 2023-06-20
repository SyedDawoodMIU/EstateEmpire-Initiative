package property.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PropertyImage extends AuditEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String downloadURL;

    private Long propertyId;

}
