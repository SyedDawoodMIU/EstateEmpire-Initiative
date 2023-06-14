package property.application.model;

import jakarta.persistence.*;
import lombok.Data;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 795642f2ce55521f5204007ae61f6feca627c2e3
@Entity
@Data
@Table(name = "app_user")
public class User extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    Long userId;
=======
    Long id;
>>>>>>> 795642f2ce55521f5204007ae61f6feca627c2e3

    @Column(unique = true)
    private String username;
    private String password;

    @Column(unique = true)
    private String email;
<<<<<<< HEAD
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(
            name = "Role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;


}


=======

}
>>>>>>> 795642f2ce55521f5204007ae61f6feca627c2e3
