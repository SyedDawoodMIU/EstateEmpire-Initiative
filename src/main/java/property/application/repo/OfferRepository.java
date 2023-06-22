package property.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import property.application.model.Offer;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
   List<Offer> findAllByPropertyPropertyId(Long propertyId);


}
