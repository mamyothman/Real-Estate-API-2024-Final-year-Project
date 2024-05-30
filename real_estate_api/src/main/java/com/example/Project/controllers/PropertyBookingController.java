package com.example.Project.controllers;

import com.example.Project.servises.PropertyBookingService;
import com.example.Project.tables.PropertyBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class PropertyBookingController {

    private final PropertyBookingService propertyBookingService;

    @Autowired
    public PropertyBookingController(PropertyBookingService propertyBookingService) {
        this.propertyBookingService = propertyBookingService;
    }

    @GetMapping
    public List<PropertyBooking> getAllBookings() {
        return propertyBookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyBooking> getBookingById(@PathVariable String id) {
        return propertyBookingService.getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PropertyBooking createBooking(@RequestBody PropertyBooking booking) {
        return propertyBookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyBooking> updateBooking(@PathVariable String id, @RequestBody PropertyBooking bookingDetails) {
        PropertyBooking propertyBooking = propertyBookingService.updateBooking(id, bookingDetails);
        return new ResponseEntity<>(propertyBooking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        propertyBookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}

