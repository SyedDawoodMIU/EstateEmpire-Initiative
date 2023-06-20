package property.application.service.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import property.application.controller.constants.BaseErrorCode;
import property.application.dto.PropertyDto;
import property.application.exception.BadRequestException;
import property.application.model.Property;
import property.application.model.Review;
import property.application.repo.PropertyRepository;
import property.application.repo.ReviewRepository;
import property.application.service.PropertyService;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private  PropertyRepository propertyRepository;
    @Autowired
    private ReviewRepository reviewRepository;


    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public List<PropertyDto> getAllProperties() {
        return propertyRepository.findAll()
                .stream().map(property -> modelMapper.map(property, PropertyDto.class)).toList();
    }

    @Override
    public PropertyDto getPropertyById(Long id) {
        var property = propertyRepository.findById(id).orElseThrow(() -> new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Property not found"));
        property.setViewCount(property.getViewCount() + 1);
        return modelMapper.map(propertyRepository.save(property), PropertyDto.class);
    }

    @Override
    public PropertyDto createProperty(PropertyDto propertyDto) {
        Property property = modelMapper.map(propertyDto, Property.class);
        property.setViewCount(0);
        return modelMapper.map(propertyRepository.save(property), PropertyDto.class);
    }

    @Override
    public PropertyDto updateProperty(Long id, PropertyDto updatedProperty) {
        Property property = modelMapper.map(updatedProperty, Property.class);
        property.setPropertyId(id);
        property.setType(updatedProperty.getType());
        property.setViewCount(updatedProperty.getViewCount());
        return modelMapper.map(propertyRepository.save(property), PropertyDto.class);
    }

    @Override
   public void  deleteProperty(Long id){
           propertyRepository.deleteById(id);
    }

//    @Override
//    public List<PropertyDto> searchProperty(String city, String state) {
//        return propertyRepository.searchProperty(city, state)
//       .stream().map(property -> modelMapper.map(property, PropertyDto.class))
//                .toList();
//    }

    public void addReview(Review review){
        reviewRepository.save(review);
    }

   }





