package com.uber_project_auth_service.DTOs;


import com.uber_project_auth_service.Models.Passenger;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerDTO {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNUmber;
    private Date createAt;

    public static PassengerDTO passengerDtoFrom(Passenger passenger){

        return PassengerDTO.builder()
                .id(passenger.getId())
                .email(passenger.getEmail())
                .name(passenger.getName())
                .phoneNUmber(passenger.getPhoneNumber())
                .createAt(passenger.getCreatedAt())
                .password(passenger.getPassword())
                .build();
    }
}
