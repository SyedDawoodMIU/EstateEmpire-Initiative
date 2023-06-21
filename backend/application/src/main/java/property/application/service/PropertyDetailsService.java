package property.application.service;

import property.application.dto.PropertyDetailsDto;
import property.application.model.PropertyDetails;

public interface PropertyDetailsService {


    PropertyDetails getPropertyDetails(Long id);

    PropertyDetails createPropertyDetails(PropertyDetailsDto propertyDetailsDto);

    PropertyDetails updatePropertyDetails(Long id, PropertyDetailsDto propertyDetailsDto);

    void deletePropertyDetails(Long id);

}
