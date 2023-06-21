package property.application.service.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import property.application.controller.constants.BaseErrorCode;
import property.application.dto.PropertyDetailsDto;
import property.application.dto.request.PropertyDtoRequest;
import property.application.dto.response.PropertyDto;
import property.application.exception.BadRequestException;
import property.application.model.Address;
import property.application.model.Property;
import property.application.model.Review;
import property.application.model.enums.PropertyType;
import property.application.repo.PropertyRepository;
import property.application.repo.ReviewRepository;
import property.application.repo.SearchPropertyByCriteria;
import property.application.service.PropertyService;
import property.application.util.FileUploadUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private  PropertyRepository propertyRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @Autowired
    private SearchPropertyByCriteria searchPropertyByCriteria;


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
    public PropertyDto createProperty(PropertyDtoRequest propertyDto) {
        Property property = modelMapper.map(propertyDto, Property.class);
        property.setViewCount(0);
        var saved = propertyRepository.save(property);
        propertyDto.getFiles()
                .forEach(file -> fileUploadUtil.uploadFile(file, saved.getPropertyId()));
        return modelMapper.map(saved, PropertyDto.class);
    }

    @Override
    public PropertyDto updateProperty(Long id, PropertyDtoRequest updatedProperty) {
        Property property = modelMapper.map(updatedProperty, Property.class);
        property.setPropertyId(id);
        property.setType(updatedProperty.getType());
        property.setViewCount(updatedProperty.getViewCount());
        var saved = propertyRepository.save(property);
        updatedProperty.getFiles()
                .forEach(file -> fileUploadUtil.uploadFile(file, saved.getPropertyId()));
        return modelMapper.map(saved, PropertyDto.class);
    }

    @Override
   public void  deleteProperty(Long id){
           propertyRepository.deleteById(id);
    }

    @Override
    public List<Property> searchPropertyByCriteria(String city, String state, String zipCode, String country, PropertyType type, int bedrooms, int bathrooms, int lotSize, Double rentAmount, int yearBuilt) {
        var dtoRequest = new PropertyDetailsDto();
        var property = new Property();
        property.setAddress(new Address());


        property.getAddress().setCity(city);
        property.getAddress().setState(state);
        property.getAddress().setZipCode(zipCode);
        property.getAddress().setCountry(country);
        property.setType(type);

        dtoRequest.setBedrooms(bedrooms);
        dtoRequest.setBathrooms(bathrooms);
        dtoRequest.setLotSize(lotSize);
        dtoRequest.setRentAmount(rentAmount);
        dtoRequest.setYearBuilt(yearBuilt);
        return searchPropertyByCriteria.findAllByCriteria(dtoRequest,property);

    }

    public void addReview(Review review){
        reviewRepository.save(review);
    }

   }





