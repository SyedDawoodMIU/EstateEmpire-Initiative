package property.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import property.application.model.Property;
import property.application.model.enums.OfferStatus;
import property.application.model.enums.PropStatus;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}

