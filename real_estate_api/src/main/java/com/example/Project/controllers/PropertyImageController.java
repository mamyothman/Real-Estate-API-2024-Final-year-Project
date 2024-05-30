package com.example.Project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.example.Project.repositories.PropertyImageRepository;
import com.example.Project.repositories.PropertyRepository;
import com.example.Project.tables.HouseAgentImage;
import com.example.Project.tables.Property;
import com.example.Project.tables.PropertyImages;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.Project.Utility.ImageUtility;

@RestController
@RequestMapping("/api/property-image")
@CrossOrigin
public class PropertyImageController {

    @Autowired
    private PropertyImageRepository propertyImageRepository;
    private ImageUtility imageUtility;

    @Autowired
    private PropertyRepository propertyRepository;

    @PostMapping(value = "/create/{property_id}", consumes = "multipart/form-data")
    @Transactional
    public ResponseEntity.BodyBuilder uploadImage(
            @RequestParam("imageFile") MultipartFile file1,
            @RequestParam("imageFile2") MultipartFile file2,
            @RequestParam("imageFile3") MultipartFile file3,
            @RequestParam("imageFile4") MultipartFile file4,
            @RequestParam("imageFile5") MultipartFile map,
            @RequestParam("imageFile6") MultipartFile top,
            @RequestParam("imageFile7") MultipartFile ground,
            @PathVariable("property_id") String property_id) throws IOException {
        PropertyImages propertyImages = new PropertyImages();
        propertyImages.setPropertyImage1(ImageUtility.compressBytes(file1.getBytes()));
        propertyImages.setPropertyImage2(ImageUtility.compressBytes(file2.getBytes()));
        propertyImages.setPropertyImage3(ImageUtility.compressBytes(file3.getBytes()));
        propertyImages.setPropertyImage4(ImageUtility.compressBytes(file4.getBytes()));
        propertyImages.setGroundmapimage(ImageUtility.compressBytes(ground.getBytes()));
        propertyImages.setTopmapimage(ImageUtility.compressBytes(top.getBytes()));
        propertyImages.setMapimage(ImageUtility.compressBytes(map.getBytes()));
        Optional<Property> property = propertyRepository.findById(property_id);
        if (property.isPresent()) {
            propertyImages.setPropertyData(property.get());
            propertyImageRepository.save(propertyImages);
            return ResponseEntity.status(HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allProperties")
    public List<PropertyImages> getAllPropertiesImage() {
        List<PropertyImages> propertyImages = propertyImageRepository.findAll();
        List<PropertyImages> new_list = new ArrayList<>();
        if (propertyImages.size() > 0) {
            for (PropertyImages property_image : propertyImages) {
                PropertyImages new_property = new PropertyImages();
                new_property.setPropertyImageId(property_image.getPropertyImageId());
                new_property.setPropertyData(property_image.getPropertyData());
                new_property.setPropertyImage1(ImageUtility.decompressBytes(property_image.getPropertyImage1()));
                new_property.setPropertyImage2(ImageUtility.decompressBytes(property_image.getPropertyImage2()));
                new_property.setPropertyImage3(ImageUtility.decompressBytes(property_image.getPropertyImage3()));
                new_property.setPropertyImage4(ImageUtility.decompressBytes(property_image.getPropertyImage4()));
                new_property.setGroundmapimage(ImageUtility.decompressBytes(property_image.getGroundmapimage()));
                new_property.setTopmapimage(ImageUtility.decompressBytes(property_image.getTopmapimage()));
                new_property.setMapimage(ImageUtility.decompressBytes(property_image.getMapimage()));
                new_list.add(new_property);
            }
        } else {
            return new ArrayList<>();
        }
        return new_list;
    }

    @GetMapping("/by-id/{id}")
    public PropertyImages getPropertyImageById(@PathVariable("id") String id) {
        Optional<PropertyImages> propertyImages = propertyImageRepository.findById(id);
        PropertyImages new_property = new PropertyImages();
        if (propertyImages.isPresent()) {
            new_property.setPropertyImageId(propertyImages.get().getPropertyImageId());
            new_property.setPropertyData(propertyImages.get().getPropertyData());
            new_property.setPropertyImage1(ImageUtility.decompressBytes(propertyImages.get().getPropertyImage1()));
            new_property.setPropertyImage2(ImageUtility.decompressBytes(propertyImages.get().getPropertyImage2()));
            new_property.setPropertyImage3(ImageUtility.decompressBytes(propertyImages.get().getPropertyImage3()));
            new_property.setPropertyImage4(ImageUtility.decompressBytes(propertyImages.get().getPropertyImage4()));
            new_property.setGroundmapimage(ImageUtility.decompressBytes(propertyImages.get().getGroundmapimage()));
            new_property.setTopmapimage(ImageUtility.decompressBytes(propertyImages.get().getTopmapimage()));
            new_property.setMapimage(ImageUtility.decompressBytes(propertyImages.get().getMapimage()));
            return new_property;
        } else {
            return new PropertyImages();
        }
    }

    @GetMapping("/by-property-id/{id}")
    public PropertyImages getPropertyImageByPropertyId(@PathVariable("id") String id) {
        Optional<PropertyImages> propertyImages = propertyImageRepository.getPropertyImageByPropertyId(id);
        PropertyImages new_property = new PropertyImages();
        if (propertyImages.isPresent()) {
            new_property.setPropertyImageId(propertyImages.get().getPropertyImageId());
            new_property.setPropertyData(propertyImages.get().getPropertyData());
            new_property.setPropertyImage1(ImageUtility.decompressBytes(propertyImages.get().getPropertyImage1()));
            new_property.setPropertyImage2(ImageUtility.decompressBytes(propertyImages.get().getPropertyImage2()));
            new_property.setPropertyImage3(ImageUtility.decompressBytes(propertyImages.get().getPropertyImage3()));
            new_property.setPropertyImage4(ImageUtility.decompressBytes(propertyImages.get().getPropertyImage4()));
            new_property.setGroundmapimage(ImageUtility.decompressBytes(propertyImages.get().getGroundmapimage()));
            new_property.setTopmapimage(ImageUtility.decompressBytes(propertyImages.get().getTopmapimage()));
            new_property.setMapimage(ImageUtility.decompressBytes(propertyImages.get().getMapimage()));
            return new_property;
        } else {
            return new PropertyImages();
        }
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletePropertyImageById(@PathVariable("id") String id){
        propertyImageRepository.deleteById(id);
        return HttpStatus.OK;
    }
}
