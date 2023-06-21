package property.application.service;

import property.application.dto.request.PropertyDtoRequest;
import property.application.dto.response.PropertyDto;

import java.util.List;

public interface PropertyService {
    List<PropertyDto> getAllProperties();
    PropertyDto getPropertyById(Long id);
    PropertyDto createProperty(PropertyDtoRequest property);
    PropertyDto updateProperty(Long id, PropertyDtoRequest updatedProperty);
    void deleteProperty(Long id);

}

