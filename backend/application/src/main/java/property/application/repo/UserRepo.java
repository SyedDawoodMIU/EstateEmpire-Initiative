package property.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import property.application.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
<<<<<<< HEAD
    User findByEmail(String email);
=======
>>>>>>> 795642f2ce55521f5204007ae61f6feca627c2e3
}
