package property.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import property.application.model.Offer;
import property.application.model.User;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
   List<Offer> findAllByPropertyPropertyId(Long propertyId);

   List<Offer> findAllByCustomer(User customer);

}
