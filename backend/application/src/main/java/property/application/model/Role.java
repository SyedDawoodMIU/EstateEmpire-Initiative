package property.application.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String roleName;


    @ManyToMany(mappedBy = "roles" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> users;

}
