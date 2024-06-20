package com.uber_project_auth_service.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking extends BaseModel{

    @Enumerated(value = EnumType.STRING)

    private BookingStatus bookingStatus;

    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(nullable = false)
    private Long totalDistance;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Passenger passenger;

}