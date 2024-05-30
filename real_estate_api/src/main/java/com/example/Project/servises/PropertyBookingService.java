package com.example.Project.servises;

import com.example.Project.Exception.ResourceNotFoundException;
import com.example.Project.repositories.PropertyBookingRepository;
import com.example.Project.repositories.PropertyRepository;
import com.example.Project.tables.Property;
import com.example.Project.tables.PropertyBooking;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyBookingService {

    private final PropertyBookingRepository propertyBookingRepository;

    @Autowired
    public PropertyBookingService(PropertyBookingRepository propertyBookingRepository) {
        this.propertyBookingRepository = propertyBookingRepository;
    }

    @Autowired
    private PropertyRepository propertyRepository;

    public List<PropertyBooking> getAllBookings() {
        return propertyBookingRepository.findAll();
    }

    public Optional<PropertyBooking> getBookingById(String id) {
        return propertyBookingRepository.findById(id);
    }

    public PropertyBooking createBooking(PropertyBooking booking) {
        Optional<Property> price = propertyRepository.findById(booking.getPropertyData().getPropertyId());
        if(price.isPresent()) {
            LocalDate startDate = booking.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = booking.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long numberOfMonths = ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1));

            double totalPrice = price.get().getPrice() * numberOfMonths;
            booking.setTotalPrice(totalPrice);

            return propertyBookingRepository.save(booking);
        } else {
            return new PropertyBooking();
        }
    }

    @Transactional
    public PropertyBooking updateBooking(String id, PropertyBooking bookingDetails) {
        return propertyBookingRepository.findById(id).map(booking -> {
            booking.setPropertyData(bookingDetails.getPropertyData());
            booking.setCustomerData(bookingDetails.getCustomerData());
            booking.setStartDate(bookingDetails.getStartDate());
            booking.setEndDate(bookingDetails.getEndDate());
            booking.setTotalPrice(bookingDetails.getTotalPrice());
            booking.setBookingStatus(bookingDetails.getBookingStatus());
            booking.setHouseAgentData(bookingDetails.getHouseAgentData());
            booking.setStatus(bookingDetails.getStatus());
            return propertyBookingRepository.save(booking);
        }).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + id));
    }

    public void deleteBooking(String id) {
        propertyBookingRepository.deleteById(id);
    }
}
