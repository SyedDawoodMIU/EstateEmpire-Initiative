package property.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import property.application.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
