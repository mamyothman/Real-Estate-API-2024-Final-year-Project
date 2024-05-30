package com.example.Project.tables;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String feedBackId;
    private String comments;
    private int rating; // e.g., rating out of 5
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "propertyId",referencedColumnName = "propertyId")
    private Property propertyData;
}

