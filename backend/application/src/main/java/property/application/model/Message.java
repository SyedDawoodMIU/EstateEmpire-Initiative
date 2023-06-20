package property.application.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Optional;

@Entity
@Data
public class Message extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageID;

    @Lob
    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    public Message(Optional<User> sender, Optional<User> receiver, String message) {
    }
}
