package property.application.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonManagedReference
    private Property property;

}
