package com.uber_project_auth_service.Controllers;

import com.uber_project_auth_service.DTOs.PassengerDTO;
import com.uber_project_auth_service.DTOs.PassengerSignupRequestDTO;
import com.uber_project_auth_service.Services.AuthService;
import com.uber_project_auth_service.Services.Impl.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControlller {

    private final AuthService authService;

    AuthControlller(AuthService authService){
        this.authService= authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDTO> signupPassenger(@RequestBody PassengerSignupRequestDTO passengerSignupRequestDTO){

        PassengerDTO response = authService.signupPassenger(passengerSignupRequestDTO);

        return new ResponseEntity<PassengerDTO>(response, HttpStatus.CREATED) ;
    }
}
