package com.example.Project.controllers;

import com.example.Project.repositories.HouseAgentRepository;
import com.example.Project.servises.HouseAgentService;
import com.example.Project.tables.HouseAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/house_agent")
@CrossOrigin
public class HouseAgentController {

    private final HouseAgentService houseAgentService;

    @Autowired
    public HouseAgentController(HouseAgentService houseAgentService) {
        this.houseAgentService = houseAgentService;
    }

    @GetMapping
    public List<HouseAgent> getAllHouseAgents() {
        return houseAgentService.getAllHouseAgents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseAgent> getHouseAgentById(@PathVariable String id) {
        return houseAgentService.getHouseAgentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HouseAgent createHouseAgent(@RequestBody HouseAgent houseAgent) {
        return houseAgentService.createHouseAgent(houseAgent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HouseAgent> updateHouseAgent(@PathVariable String id, @RequestBody HouseAgent houseAgentDetails) {
        HouseAgent house = houseAgentService.updateHouseAgent(id, houseAgentDetails);
        return new ResponseEntity<>(house, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouseAgent(@PathVariable String id) {
        houseAgentService.deleteHouseAgent(id);
        return ResponseEntity.noContent().build();
    }
}
