package com.example.Project.repositories;

import com.example.Project.tables.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login,String> {

    @Query(value = "SELECT * from login where username = ?1",nativeQuery = true)
    Optional<Login> check_existing_user(String username);
    @Query(value = "SELECT * from login WHERE username = ?1 and password = ?2",nativeQuery=true)
    Optional<Login> login_authentication(String username,String password);
}
