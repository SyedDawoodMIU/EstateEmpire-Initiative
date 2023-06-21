package property.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import property.application.dto.request.PropertyDtoRequest;
import property.application.dto.response.PropertyDto;
import property.application.model.Property;
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
    public ResponseEntity<?> save(@ModelAttribute PropertyDtoRequest p){
       return ResponseEntity.ok(propertyService.createProperty(p));
    }
    @GetMapping
    public ResponseEntity<?> getAllProperty(){
      List<PropertyDto> propertyDto = propertyService.getAllProperties();
        return  ResponseEntity.ok(propertyDto);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPropertyById(@PathVariable("id") Long id){
        PropertyDto propertyDto = propertyService.getPropertyById(id);
        return  ResponseEntity.ok(propertyDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable("id") Long id){
        propertyService.deleteProperty(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void updateProperty(@PathVariable("id") Long id, @RequestBody PropertyDtoRequest propertyDto){
        propertyService.updateProperty(id,propertyDto);
    }

    @GetMapping("/types")
    public List<String> getPropertyType(){
        return Arrays.stream(PropertyType.values())
                .map(Enum::name).collect(Collectors.toList());
    }

    @GetMapping("/search")
  public List<Property> searchPropertyByCriteria(@RequestParam(required = false) String city,
                                                 @RequestParam(required = false) String state,
                                                 @RequestParam(required = false) String zipCode,
                                                 @RequestParam(required = false) String country,
                                                 @RequestParam(required = false) PropertyType type,
                                                 @RequestParam(required = false) Integer bedrooms,
                                                 @RequestParam(required = false) Integer bathrooms,
                                                 @RequestParam (required = false)Integer lotSize,
                                                 @RequestParam (required = false)Double rentAmount,
                                                 @RequestParam (required = false)Integer yearBuilt){



        // Create a new Property object and set the address property
        Property property = new Property();


        return propertyService.searchPropertyByCriteria(city,state,zipCode,country,type,bedrooms,bathrooms,lotSize,rentAmount,yearBuilt);
    }



}
