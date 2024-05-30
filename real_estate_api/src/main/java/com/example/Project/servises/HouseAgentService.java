package com.example.Project.servises;

import com.example.Project.Exception.ResourceNotFoundException;
import com.example.Project.repositories.HouseAgentRepository;
import com.example.Project.tables.HouseAgent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseAgentService {

    private final HouseAgentRepository houseAgentRepository;

    @Autowired
    public HouseAgentService(HouseAgentRepository houseAgentRepository) {
        this.houseAgentRepository = houseAgentRepository;
    }

    public List<HouseAgent> getAllHouseAgents() {
        return houseAgentRepository.findAll();
    }

    public Optional<HouseAgent> getHouseAgentById(String id) {
        return houseAgentRepository.findById(id);
    }

    public HouseAgent createHouseAgent(HouseAgent houseAgent) {
        return houseAgentRepository.save(houseAgent);
    }

    @Transactional
    public HouseAgent updateHouseAgent(String id, HouseAgent houseAgentDetails) {
        return houseAgentRepository.findById(id).map(houseAgent -> {
            houseAgent.setFirstName(houseAgentDetails.getFirstName());
            houseAgent.setLastName(houseAgentDetails.getLastName());
            houseAgent.setEmail(houseAgentDetails.getEmail());
            houseAgent.setPhone(houseAgentDetails.getPhone());
            houseAgent.setAddress(houseAgentDetails.getAddress());
            houseAgent.setCity(houseAgentDetails.getCity());
            houseAgent.setState(houseAgentDetails.getState());
            houseAgent.setZipCode(houseAgentDetails.getZipCode());
            houseAgent.setCountry(houseAgentDetails.getCountry());
            houseAgent.setLicenseNumber(houseAgentDetails.getLicenseNumber());
            houseAgent.setAgencyName(houseAgentDetails.getAgencyName());
            houseAgent.setAgencyAddress(houseAgentDetails.getAgencyAddress());
            houseAgent.setAgencyPhone(houseAgentDetails.getAgencyPhone());
            houseAgent.setAgencyEmail(houseAgentDetails.getAgencyEmail());
            houseAgent.setYearsOfExperience(houseAgentDetails.getYearsOfExperience());
            houseAgent.setSpecialties(houseAgentDetails.getSpecialties());
            houseAgent.setDescription(houseAgentDetails.getDescription());
            houseAgent.setIsActive(houseAgentDetails.getIsActive());
            houseAgent.setUserData(houseAgent.getUserData());
            return houseAgentRepository.save(houseAgent);
        }).orElseThrow(() -> new ResourceNotFoundException("HouseAgent not found with id " + id));
    }

    public void deleteHouseAgent(String id) {
        houseAgentRepository.deleteById(id);
    }
}

