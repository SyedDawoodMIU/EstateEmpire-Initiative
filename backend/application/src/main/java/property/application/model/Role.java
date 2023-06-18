package property.application.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(unique = true, nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles" ,fetch = FetchType.EAGER)
    private List<User> users;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
