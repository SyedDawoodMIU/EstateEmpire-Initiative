package property.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import property.application.dto.PropertySearchCriteria;
import property.application.dto.request.PropertyDtoRequest;
import property.application.dto.response.PropertyDto;
import property.application.model.enums.PropertyType;
import property.application.service.PropertyService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("properties")
@RestController
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PropertyDtoRequest p) {
        return ResponseEntity.ok(propertyService.createProperty(p));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPropertyById(@PathVariable("id") Long id) {
        PropertyDto propertyDto = propertyService.getPropertyById(id);
        return ResponseEntity.ok(propertyDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable("id") Long id) {
        propertyService.deleteProperty(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void updateProperty(@PathVariable("id") Long id, @RequestBody PropertyDtoRequest propertyDto) {
        propertyService.updateProperty(id, propertyDto);
    }

    @GetMapping("/types")
    public List<String> getPropertyType() {
        return Arrays.stream(PropertyType.values()).map(Enum::name).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PropertyDto> searchPropertyByCriteria(@ModelAttribute PropertySearchCriteria searchCriteria) {
        return propertyService.searchPropertyByCriteria(searchCriteria);
    }

    @PatchMapping("/{id}")
    public void favoriteProperty(@PathVariable("id") Long propertyId){
        propertyService.favoriteProperty(propertyId);
    }

    @GetMapping("/favorite")
    public List<PropertyDto> getFavoriteList(){
        return propertyService.favoriteList();
    }

}
