package property.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import property.application.dto.PropertyDto;
import property.application.service.PropertyService;

import java.util.List;

@RequestMapping("properties")
@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PropertyDto p){
       return ResponseEntity.ok( propertyService.createProperty(p));
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
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable("id") Long id){
        propertyService.deleteProperty(id);
        return  ResponseEntity.ok(HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable("id") Long id, @RequestBody PropertyDto propertyDto){
        propertyService.updateProperty(id,propertyDto);
        return  ResponseEntity.ok(HttpStatus.OK);

    }
//    @GetMapping("/search")
//    public ResponseEntity<?> searchProperty(@RequestParam("city") String city, @RequestParam("state") String state){
//        List<PropertyDto> propertyDto = propertyService.searchProperty(city,state);
//        return  ResponseEntity.ok(propertyDto);
//
//    }


}
