package com.example.Project.repositories;


import com.example.Project.tables.PropertyBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyBookingRepository extends JpaRepository<PropertyBooking,String> {
}
