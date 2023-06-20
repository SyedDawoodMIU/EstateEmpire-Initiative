package property.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import property.application.dto.PropertyDto;
import property.application.model.Property;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {



}

