package property.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String comment;
    private Integer numberOfStars;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "property_id")
    private Property property;
}
