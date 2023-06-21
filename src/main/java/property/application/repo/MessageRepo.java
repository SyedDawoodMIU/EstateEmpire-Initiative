package property.application.repo;

import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import property.application.model.Message;
import property.application.model.User;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    List<Message> findAllByReceiver(User receiver);


}
