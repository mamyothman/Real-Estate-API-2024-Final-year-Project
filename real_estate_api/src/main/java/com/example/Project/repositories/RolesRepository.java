package com.example.Project.repositories;

import com.example.Project.tables.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {
    @Query(value = "SELECT * from roles where role_name = ?1",nativeQuery=true)
    Optional<Roles> roleByName(String roleName);
}
