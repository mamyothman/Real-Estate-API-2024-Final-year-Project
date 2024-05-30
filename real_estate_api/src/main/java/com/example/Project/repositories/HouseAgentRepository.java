package com.example.Project.repositories;

import com.example.Project.tables.HouseAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseAgentRepository extends JpaRepository<HouseAgent, String> {
}

