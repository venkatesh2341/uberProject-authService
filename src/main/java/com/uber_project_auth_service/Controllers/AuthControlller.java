package com.uber_project_auth_service.Controllers;

import com.uber_project_auth_service.DTOs.PassengerSignupRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControlller {

    @PostMapping("/signup/passenger")
    public ResponseEntity<?> signupPassenger(@RequestBody PassengerSignupRequestDTO passengerSignupRequestDTO){
        return null;
    }
}
