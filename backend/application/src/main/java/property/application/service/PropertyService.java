package property.application.service;

import property.application.dto.request.PropertyDtoRequest;
import property.application.dto.response.PropertyDto;
import property.application.model.Property;
import property.application.model.enums.PropertyType;

import java.util.List;

public interface PropertyService {
    List<PropertyDto> getAllProperties();
    PropertyDto getPropertyById(Long id);
    PropertyDto createProperty(PropertyDtoRequest property);
    PropertyDto updateProperty(Long id, PropertyDtoRequest updatedProperty);
    void deleteProperty(Long id);

    List<Property> searchPropertyByCriteria(String city, String state, String zipCode, String country, PropertyType type, int bedrooms, int bathrooms, int lotSize, Double rentAmount, int yearBuilt);

}

