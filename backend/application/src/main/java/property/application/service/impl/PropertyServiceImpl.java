package property.application.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import property.application.dto.PropertyDto;
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
    public List<PropertyDto> getAllProperties() {
        return propertyRepository.findAll()
                .stream().map(property -> modelMapper.map(property, PropertyDto.class)).toList();
    }

    @Override
    public PropertyDto getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .map(property -> modelMapper.map(property, PropertyDto.class)).orElse(null);
    }

    @Override
    public PropertyDto createProperty(PropertyDto propertyDto) {
        Property property = modelMapper.map(propertyDto, Property.class);
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

    @Override
    public List<PropertyDto> searchProperty(String city, String state) {
//        return propertyRepository.searchProperty(city, state)
//       .stream().map(property -> modelMapper.map(property, PropertyDto.class))
//                .toList();
        return null;

    }

    public void addReview(Review review){
        reviewRepository.save(review);
    }

   }





