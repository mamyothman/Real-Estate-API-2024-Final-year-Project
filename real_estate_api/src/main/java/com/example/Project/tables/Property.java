package com.example.Project.tables;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String propertyId;
    private String title;
    private String description;
    private String type; // e.g., "Commercial", "Corporate", "Detached", "Retail"
    private Integer floorNumber; // For Office and Shop
    private Integer totalFloors; // For Office and Shop
    private Integer squareFootage;
    private Double price;
    private String location;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private Integer buildingAge; // For Office and Shop
    private Integer yearBuilt; // For House
    private Integer bedrooms; // For House
    private Integer bathrooms; // For House
    private Integer balconies; // For House
    private Integer kitchen; // For House
    private Integer livingRooms; // For House
    private String amenities; // e.g., "Elevator, Parking, Security, Garden, Garage, Pool"
    private String status; // e.g., "Available", "Sold"
    private Integer isFeatured;
    @ManyToOne
    @JoinColumn(name = "agentId",referencedColumnName = "agentId")
    private HouseAgent houseAgentData;
}

