package property.application.service;

import property.application.dto.PropertyDto;

import java.util.List;

public interface PropertyService {
    List<PropertyDto> getAllProperties();
    PropertyDto getPropertyById(Long id);
    PropertyDto createProperty(PropertyDto property);
    PropertyDto updateProperty(Long id, PropertyDto updatedProperty);
    void deleteProperty(Long id);


    List<PropertyDto> searchProperty(String city, String state);
}
