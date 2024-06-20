package com.uber_project_auth_service.DTOs;


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
}
