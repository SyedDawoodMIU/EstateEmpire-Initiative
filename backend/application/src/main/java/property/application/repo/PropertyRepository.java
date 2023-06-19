package property.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import property.application.dto.PropertyDto;
import property.application.model.Property;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
//@Query("select p from Property p where p.propertyDetails.address.city = :city and p.propertyDetails.address.state = :state")
//    List<PropertyDto> searchProperty(String city, String state);

}
