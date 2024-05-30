package com.example.Project.tables;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class HouseAgentImage {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String imageId;
    private String imageName;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profileImage;
    @ManyToOne
    @JoinColumn(name = "agentId",referencedColumnName = "agentId")
    private HouseAgent houseAgentData;
}
