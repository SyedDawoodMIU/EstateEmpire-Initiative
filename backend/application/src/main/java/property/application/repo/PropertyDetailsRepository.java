package property.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import property.application.model.PropertyDetails;

public interface PropertyDetailsRepository extends JpaRepository<PropertyDetails, Long> {



}
