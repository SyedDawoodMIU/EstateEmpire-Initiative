package property.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import property.application.dto.PropertyDetailsDto;
import property.application.model.PropertyDetails;
import property.application.repo.PropertyDetailsRepository;
import property.application.service.PropertyDetailsService;
@Service
public class PropertyDetailsServiceImpl implements PropertyDetailsService {

    private final PropertyDetailsRepository propertyDetailsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PropertyDetailsServiceImpl(PropertyDetailsRepository propertyDetailsRepository, ModelMapper modelMapper) {
        this.propertyDetailsRepository = propertyDetailsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PropertyDetails getPropertyDetails(Long id) {
        return propertyDetailsRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public PropertyDetails createPropertyDetails(PropertyDetailsDto propertyDetailsDto) {
        PropertyDetails propertyDetails = modelMapper.map(propertyDetailsDto, PropertyDetails.class);
        return propertyDetailsRepository.save(propertyDetails);
    }

    @Override
    public PropertyDetails updatePropertyDetails(Long id, PropertyDetailsDto propertyDetailsDto) {
        PropertyDetails existingPropertyDetails = getPropertyDetails(id);
        modelMapper.map(propertyDetailsDto, existingPropertyDetails);
        return propertyDetailsRepository.save(existingPropertyDetails);
    }

    @Override
    public void deletePropertyDetails(Long id) {
        PropertyDetails propertyDetails = getPropertyDetails(id);
        propertyDetailsRepository.delete(propertyDetails);
    }

}

