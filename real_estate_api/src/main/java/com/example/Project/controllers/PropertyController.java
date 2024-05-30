package com.example.Project.controllers;

import com.example.Project.servises.PropertyService;
import com.example.Project.tables.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/all")
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable("id") String pid) {
        return propertyService.getPropertyById(pid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateHouseShopOffice(@PathVariable String id, @RequestBody Property houseShopOfficeDetails) {
        Property property = propertyService.updateProperty(id, houseShopOfficeDetails);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @PostMapping("/create")
    public Property createProperty(@RequestBody Property property) {
        return propertyService.createProperty(property);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable("id") String pid) {
        propertyService.deleteProperty(pid);
        return ResponseEntity.noContent().build();
    }
}


