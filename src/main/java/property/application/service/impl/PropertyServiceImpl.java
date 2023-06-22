package property.application.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import property.application.controller.constants.BaseErrorCode;
import property.application.dto.PropertySearchCriteria;
import property.application.dto.request.PropertyDtoRequest;
import property.application.dto.response.PropertyDto;
import property.application.exception.BadRequestException;
import property.application.model.Property;
import property.application.model.PropertyImage;
import property.application.model.Review;
import property.application.repo.*;
import property.application.service.PropertyService;
import property.application.util.Base64FileUpload;
import property.application.util.FileUploadUtil;
import property.application.util.LoggedinUserUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final ModelMapper modelMapper;

    private final PropertyRepository propertyRepository;

    private final ReviewRepository reviewRepository;

    private final FileUploadUtil fileUploadUtil;

    private final SearchPropertyByCriteria searchPropertyByCriteria;

    private final LoggedinUserUtil loggedinUserUtil;

    private final UserRepo userRepo;

    private final Base64FileUpload base64FileUpload;

    private final PropertyImageRepo propertyImageRepo;

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
                var downloadUrl = base64FileUpload.uploadBase64(file.getBase64Content(), saved.getPropertyId(), file.getFileName());

                var image = PropertyImage.builder()
                        .property(saved)
                        .downloadURL(downloadUrl)
                        .build();
                propertyImageRepo.save(image);
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
                var downloadUrl = base64FileUpload.uploadBase64(file.getBase64Content(), saved.getPropertyId(), file.getFileName());

                var image = PropertyImage.builder()
                        .property(saved)
                        .downloadURL(downloadUrl)
                        .build();
                propertyImageRepo.save(image);
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





