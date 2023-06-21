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
import property.application.dto.PropertySearchCriteria;
import property.application.model.Property;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchPropertyByCriteria {
    private final EntityManager em;

    public List<Property> findAllByCriteria(PropertySearchCriteria propertySearchCriteria) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Property> criteriaQuery = criteriaBuilder.createQuery(Property.class);
        List<Predicate> predicates = new ArrayList<>();

        // select * from Property
        Root<Property> root = criteriaQuery.from(Property.class);

        if (propertySearchCriteria.getType() != null) {
            Predicate typePredicate = criteriaBuilder.equal(root.get("type"), propertySearchCriteria.getType());
            predicates.add(typePredicate);
        }

        if (propertySearchCriteria.getZipCode() != null) {
            Predicate zipCodePredicate = criteriaBuilder.like(root.get("address").get("zipCode"), propertySearchCriteria.getZipCode());
            predicates.add(zipCodePredicate);
        }

        if (propertySearchCriteria.getCity() != null) {
            Predicate cityPredicate = criteriaBuilder.like(root.get("address").get("city"), propertySearchCriteria.getCity());
            predicates.add(cityPredicate);
        }
        if (propertySearchCriteria.getState() != null) {
            Predicate statePredicate = criteriaBuilder.like(root.get("address").get("state"), propertySearchCriteria.getState());
            predicates.add(statePredicate);
        }
        if (propertySearchCriteria.getCountry() != null) {
            Predicate countryPredicate = criteriaBuilder.like(root.get("address").get("country"), propertySearchCriteria.getCountry());
            predicates.add(countryPredicate);
        }

        if (propertySearchCriteria.getRentAmount() != null) {
            Predicate rentAmountPredicate = criteriaBuilder.equal(root.get("propertyDetails").get("rentAmount"), propertySearchCriteria.getRentAmount());
            predicates.add(rentAmountPredicate);
        }

        if (propertySearchCriteria.getBathrooms() != 0) {
            Predicate numberOfBathroomsPredicate = criteriaBuilder.equal(root.get("propertyDetails").get("bedrooms"), propertySearchCriteria.getBathrooms());
            predicates.add(numberOfBathroomsPredicate);
        }
        if (propertySearchCriteria.getBedrooms() != 0) {
            Predicate numberOfBedroomsPredicate = criteriaBuilder.equal(root.get("propertyDetails").get("bathrooms"), propertySearchCriteria.getBedrooms());
            predicates.add(numberOfBedroomsPredicate);
        }

        if(propertySearchCriteria.getYearBuilt()!=0){
            Predicate yearBuiltPredicate = criteriaBuilder.equal(root.get("propertyDetails").get("yearBuilt"), propertySearchCriteria.getYearBuilt());
            predicates.add(yearBuiltPredicate);
        }

        if(propertySearchCriteria.getLotSize()!=0){
            Predicate lotSizePredicate = criteriaBuilder.equal(root.get("propertyDetails").get("lotSize"), propertySearchCriteria.getLotSize());
            predicates.add(lotSizePredicate);
        }
        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<Property> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
