package property.application.service.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import property.application.controller.constants.BaseErrorCode;
import property.application.dto.PropertyDetailsDto;
import property.application.dto.PropertySearchCriteria;
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
import property.application.repo.UserRepo;
import property.application.service.PropertyService;
import property.application.util.Base64Utils;
import property.application.util.FileUploadUtil;
import property.application.util.LoggedinUserUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @Autowired
    private SearchPropertyByCriteria searchPropertyByCriteria;
    @Autowired
    private LoggedinUserUtil loggedinUserUtil;
    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public List<PropertyDto> getAllProperties() {
        return propertyRepository.findAll().stream().map(property -> modelMapper.map(property, PropertyDto.class)).toList();
    }

    @Override
    public PropertyDto getPropertyById(Long id) {
        var property = propertyRepository.findById(id).orElseThrow(() -> new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Property not found"));
        if (!property.getOwner().getEmail().equals(loggedinUserUtil.getUserEmail())) {
            property.setViewCount(property.getViewCount() + 1);
        }
        return modelMapper.map(propertyRepository.save(property), PropertyDto.class);
    }

    @Override
    public PropertyDto createProperty(PropertyDtoRequest propertyDto) {
        Property property = modelMapper.map(propertyDto, Property.class);
        property.setViewCount(0);
        property.setOwner(loggedinUserUtil.getCurrentUser());
        var saved = propertyRepository.save(property);
        if (propertyDto.getFiles() != null) {
            propertyDto.getFiles().forEach(file -> {
                try {
                    fileUploadUtil.uploadBase64(file.getBase64Content(), saved.getPropertyId(), file.getFileName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
        }
        return modelMapper.map(saved, PropertyDto.class);
    }

    @Override
    public PropertyDto updateProperty(Long id, PropertyDtoRequest updatedProperty) {
        Property property = modelMapper.map(updatedProperty, Property.class);
        property.setPropertyId(id);
        property.setType(updatedProperty.getType());
        property.setViewCount(updatedProperty.getViewCount());
        var saved = propertyRepository.save(property);
        if (updatedProperty.getFiles() != null) {
            updatedProperty.getFiles().forEach(file -> {
                try {
                    fileUploadUtil.uploadFile(Base64Utils.convertBase64ToMultipartFile(file.getBase64Content()), saved.getPropertyId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return modelMapper.map(saved, PropertyDto.class);
    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public List<PropertyDto> searchPropertyByCriteria(PropertySearchCriteria propertySearchCriteria) {
        var data = searchPropertyByCriteria.findAllByCriteria(propertySearchCriteria);
        var user = loggedinUserUtil.getCurrentUser();
        data.forEach(property -> {
                property.setIsFavorite(property.getUsers().contains(user));
        });
        return data.stream().map(property -> modelMapper.map(property, PropertyDto.class)).toList();
    }

    @Override
    public void favoriteProperty(Long id) {
        var property = propertyRepository.findById(id).orElseThrow(() -> new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Property not found"));
        var user = loggedinUserUtil.getCurrentUser();
        if (user.getFavoriteProperty().contains(property)) {
            user.getFavoriteProperty().remove(property);
        } else {
            user.getFavoriteProperty().add(property);
        }
        userRepo.save(user);
    }

    @Override
    public List<PropertyDto> favoriteList() {
        var user = loggedinUserUtil.getCurrentUser();
        return user.getFavoriteProperty().stream().map(property -> {
            var p = modelMapper.map(property, PropertyDto.class);
            p.setIsFavorite(true);
            return p;
        }).toList();
    }

    public void addReview(Review review) {
        reviewRepository.save(review);
    }

}





