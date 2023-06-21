package property.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import property.application.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
