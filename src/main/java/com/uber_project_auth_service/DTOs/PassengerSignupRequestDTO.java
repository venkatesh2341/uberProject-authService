package com.uber_project_auth_service.DTOs;


import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSignupRequestDTO {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
}
