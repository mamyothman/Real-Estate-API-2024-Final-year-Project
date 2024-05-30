package com.example.Project.repositories;

import com.example.Project.tables.PropertyImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImages,String> {

    @Query(value = "SELECT * FROM property_images where property_id = ?1",nativeQuery = true)
    Optional<PropertyImages> getPropertyImageByPropertyId(String id);
}
