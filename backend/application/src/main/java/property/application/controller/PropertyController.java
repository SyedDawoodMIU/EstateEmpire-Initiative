package property.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import property.application.dto.PropertyDto;
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
    public ResponseEntity<?> save(@RequestBody PropertyDto p){
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
    public void updateProperty(@PathVariable("id") Long id, @RequestBody PropertyDto propertyDto){
        propertyService.updateProperty(id,propertyDto);
    }

    @GetMapping("/types")
    public List<String> getPropertyType(){
        return Arrays.stream(PropertyType.values())
                .map(Enum::name).collect(Collectors.toList());
    }

//    @GetMapping("/search")
//    public ResponseEntity<?> searchProperty(@RequestParam("city") String city, @RequestParam("state") String state){
//        List<PropertyDto> propertyDto = propertyService.searchProperty(city,state);
//        return  ResponseEntity.ok(propertyDto);
//
//    }

}
