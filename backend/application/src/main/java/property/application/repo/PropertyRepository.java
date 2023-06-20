package property.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import property.application.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {



}

