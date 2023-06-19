package property.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import property.application.model.PropertyImage;

@Repository
public interface PropertyImageRepo extends JpaRepository<PropertyImage, Long> {
}
