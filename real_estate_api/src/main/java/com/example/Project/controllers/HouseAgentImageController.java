package com.example.Project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.Project.repositories.HouseAgentRepository;
import com.example.Project.tables.HouseAgent;
import com.example.Project.tables.HouseAgentImage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.Project.Utility.ImageUtility;
import com.example.Project.repositories.HouseAgentImageRepository;

@RestController
@RequestMapping("/api/house-agent-image")
@CrossOrigin
public class HouseAgentImageController {

    @Autowired
    private HouseAgentImageRepository houseAgentImageRepository;
    private ImageUtility imageUtility;

    @Autowired
    private HouseAgentRepository houseAgentRepository;

    @PostMapping(value = "/create/{agent_id}", consumes = "multipart/form-data")
    @Transactional
    public ResponseEntity.BodyBuilder uploadImage(
            @RequestParam("imageFile") MultipartFile file,
            @PathVariable("agent_id") String agent_id) throws IOException {
        HouseAgentImage houseAgentImage = new HouseAgentImage();
        houseAgentImage.setImageName(file.getOriginalFilename());
        houseAgentImage.setProfileImage(ImageUtility.compressBytes(file.getBytes()));
        Optional<HouseAgent> h = houseAgentRepository.findById(agent_id);
        if (h.isPresent()) {
            houseAgentImage.setHouseAgentData(h.get());
            houseAgentImageRepository.save(houseAgentImage);
            return ResponseEntity.status(HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allAgents")
    public List<HouseAgentImage> getAllHouseAgentImage() {
        List<HouseAgentImage> agent = houseAgentImageRepository.findAll();
        List<HouseAgentImage> new_list = new ArrayList<>();
        if (agent.size() > 0) {
            for (HouseAgentImage agent_image : agent) {
                HouseAgentImage new_agent = new HouseAgentImage();
                new_agent.setImageId(agent_image.getImageId());
                new_agent.setHouseAgentData(agent_image.getHouseAgentData());
                new_agent.setImageName(agent_image.getImageName());
                new_agent.setProfileImage(ImageUtility.decompressBytes(agent_image.getProfileImage()));
                new_list.add(new_agent);
            }
        } else {
            return new ArrayList<>();
        }
        return new_list;
    }

    @GetMapping("/by-id/{id}")
    public HouseAgentImage getAgentImageById(@PathVariable("id") String id) {
        Optional<HouseAgentImage> agent = houseAgentImageRepository.findById(id);
        HouseAgentImage new_agent = new HouseAgentImage();
        if (agent.isPresent()) {
            new_agent.setImageId(agent.get().getImageId());
            new_agent.setHouseAgentData(agent.get().getHouseAgentData());
            new_agent.setImageName(agent.get().getImageName());
            new_agent.setProfileImage(ImageUtility.decompressBytes(agent.get().getProfileImage()));;
            return new_agent;
        } else {
            return new HouseAgentImage();
        }
    }

    @GetMapping("/by-agent-id/{id}")
    public List<HouseAgentImage> findByAgentId(@PathVariable("id") String id) {
        List<HouseAgentImage> agent = houseAgentImageRepository.findByAgentId(id);
        List<HouseAgentImage> new_list = new ArrayList<>();
        if (agent.size() > 0) {
            for (HouseAgentImage agent_image : agent) {
                HouseAgentImage new_agent = new HouseAgentImage();
                new_agent.setImageId(agent_image.getImageId());
                new_agent.setHouseAgentData(agent_image.getHouseAgentData());
                new_agent.setImageName(agent_image.getImageName());
                new_agent.setProfileImage(ImageUtility.decompressBytes(agent_image.getProfileImage()));
                new_list.add(new_agent);
            }
        } else {
            return new ArrayList<>();
        }
        return new_list;
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteHouseAgentById(@PathVariable("id") String id){
        houseAgentImageRepository.deleteById(id);
        return HttpStatus.OK;
    }

}
