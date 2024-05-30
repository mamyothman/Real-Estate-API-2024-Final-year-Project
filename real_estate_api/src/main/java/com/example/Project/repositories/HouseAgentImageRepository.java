package com.example.Project.repositories;

import com.example.Project.tables.HouseAgentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HouseAgentImageRepository extends JpaRepository<HouseAgentImage,String> {

    @Query(value = "SELECT * from house_agent_image where agent_id = ?1",nativeQuery = true)
    List<HouseAgentImage> findByAgentId(String agent_id);
}
