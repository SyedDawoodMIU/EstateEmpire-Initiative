package property.application.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import property.application.dto.PropertyDetailsDto;
import property.application.model.Property;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchPropertyByCriteria {
    private final EntityManager em;
    public List<Property> findAllByCriteria(PropertyDetailsDto propertyDetailsDto, Property property) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        // select * from Property
        Root<Property> root = criteriaQuery.from(Property.class);

        if (property.getType() != null) {
            Predicate typePredicate = criteriaBuilder.equal(root.get("type"), property.getType());
            predicates.add(typePredicate);
        }

        if (property.getAddress().getZipCode() != null) {
            Predicate zipCodePredicate = criteriaBuilder.like(root.get("address").get("zipCode"), property.getAddress().getZipCode());
            predicates.add(zipCodePredicate);
        }

        if (property.getAddress().getCity() != null) {
            Predicate cityPredicate = criteriaBuilder.like(root.get("address").get("city"), property.getAddress().getCity());
            predicates.add(cityPredicate);
        }
        if (property.getAddress().getState() != null) {
            Predicate statePredicate = criteriaBuilder.like(root.get("address").get("state"), property.getAddress().getState());
            predicates.add(statePredicate);
        }
        if (property.getAddress().getCountry() != null) {
            Predicate countryPredicate = criteriaBuilder.like(root.get("address").get("country"), property.getAddress().getCountry());
            predicates.add(countryPredicate);
        }

        if (!propertyDetailsDto.getRentAmount().equals(0.0)) {
            Predicate rentAmountPredicate = criteriaBuilder.equal(root.get("propertyDetails").get("rentAmount"), propertyDetailsDto.getRentAmount());
            predicates.add(rentAmountPredicate);
        }

        if (propertyDetailsDto.getBathrooms() != 0) {
            Predicate numberOfBathroomsPredicate = criteriaBuilder.equal(root.get("propertyDetails").get("bedrooms"), propertyDetailsDto.getBathrooms());
            predicates.add(numberOfBathroomsPredicate);
        }
        if (propertyDetailsDto.getBedrooms() != 0) {
            Predicate numberOfBedroomsPredicate = criteriaBuilder.equal(root.get("propertyDetails").get("bathrooms"), propertyDetailsDto.getBedrooms());
            predicates.add(numberOfBedroomsPredicate);
        }

        if(propertyDetailsDto.getYearBuilt()!=0){
            Predicate yearBuiltPredicate = criteriaBuilder.equal(root.get("propertyDetails").get("yearBuilt"), propertyDetailsDto.getYearBuilt());
            predicates.add(yearBuiltPredicate);
        }

        if(propertyDetailsDto.getLotSize()!=0){
            Predicate lotSizePredicate = criteriaBuilder.equal(root.get("propertyDetails").get("lotSize"), propertyDetailsDto.getLotSize());
            predicates.add(lotSizePredicate);
        }
        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<Property> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
