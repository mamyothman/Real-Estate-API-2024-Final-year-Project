package com.example.Project.tables;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Entity
@Data
public class PropertyBooking {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String bookingId;
    @ManyToOne
    @JoinColumn(name = "propertyId", referencedColumnName="propertyId")
    private Property propertyData;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName="customerId")
    private Customer customerData;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private double totalPrice;
    private int bookingStatus;
    @OneToOne
    @JoinColumn(name="agentId",referencedColumnName = "agentId")
    private HouseAgent houseAgentData;
    private String status;
}
