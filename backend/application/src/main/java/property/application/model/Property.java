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
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "details_id")
//    private PropertyDetails propertyDetails;
    @OneToMany
    @JoinColumn(name = "review_id")
    private List<Review> review;



}
