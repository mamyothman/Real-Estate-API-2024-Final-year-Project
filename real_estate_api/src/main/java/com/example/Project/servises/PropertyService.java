package com.example.Project.servises;

import com.example.Project.Exception.ResourceNotFoundException;
import com.example.Project.repositories.PropertyRepository;
import com.example.Project.tables.Property;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(String pid) {
        return propertyRepository.findById(pid);
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public void deleteProperty(String pid) {
        propertyRepository.deleteById(pid);
    }

    @Transactional
    public Property updateProperty(String id, Property houseShopOfficeDetails) {
        return propertyRepository.findById(id).map(houseShopOffice -> {
            houseShopOffice.setTitle(houseShopOfficeDetails.getTitle());
            houseShopOffice.setDescription(houseShopOfficeDetails.getDescription());
            houseShopOffice.setType(houseShopOfficeDetails.getType());
            houseShopOffice.setFloorNumber(houseShopOfficeDetails.getFloorNumber());
            houseShopOffice.setTotalFloors(houseShopOfficeDetails.getTotalFloors());
            houseShopOffice.setSquareFootage(houseShopOfficeDetails.getSquareFootage());
            houseShopOffice.setPrice(houseShopOfficeDetails.getPrice());
            houseShopOffice.setLocation(houseShopOfficeDetails.getLocation());
            houseShopOffice.setCity(houseShopOfficeDetails.getCity());
            houseShopOffice.setState(houseShopOfficeDetails.getState());
            houseShopOffice.setCountry(houseShopOfficeDetails.getCountry());
            houseShopOffice.setZipcode(houseShopOfficeDetails.getZipcode());
            houseShopOffice.setBuildingAge(houseShopOfficeDetails.getBuildingAge());
            houseShopOffice.setYearBuilt(houseShopOfficeDetails.getYearBuilt());
            houseShopOffice.setBedrooms(houseShopOfficeDetails.getBedrooms());
            houseShopOffice.setBathrooms(houseShopOfficeDetails.getBathrooms());
            houseShopOffice.setBalconies(houseShopOfficeDetails.getBalconies());
            houseShopOffice.setKitchen(houseShopOfficeDetails.getKitchen());
            houseShopOffice.setLivingRooms(houseShopOfficeDetails.getLivingRooms());
            houseShopOffice.setAmenities(houseShopOfficeDetails.getAmenities());
            houseShopOffice.setStatus(houseShopOfficeDetails.getStatus());
            houseShopOffice.setIsFeatured(houseShopOfficeDetails.getIsFeatured());
            return propertyRepository.save(houseShopOffice);
        }).orElseThrow(() -> new ResourceNotFoundException("HouseShopOffice not found with id " + id));
    }
}

