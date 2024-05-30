package com.example.Project.tables;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
public class Payments {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String paymentId;
    private String controllNumber;
    @ManyToOne
    @JoinColumn(name = "bookingId",referencedColumnName = "bookingId")
    private PropertyBooking propertyBookingData;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    private Customer customerData;
    private int payment_status;
}
