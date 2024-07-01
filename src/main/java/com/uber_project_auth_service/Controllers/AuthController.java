package com.uber_project_auth_service.Controllers;

import com.uber_project_auth_service.DTOs.AuthRequestDTO;
import com.uber_project_auth_service.DTOs.AuthResponseDTO;
import com.uber_project_auth_service.DTOs.PassengerDTO;
import com.uber_project_auth_service.DTOs.PassengerSignupRequestDTO;
import com.uber_project_auth_service.Services.AuthService;
import com.uber_project_auth_service.Services.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    AuthController(AuthService authService, AuthenticationManager authenticationManager,
                    JwtService jwtService){
        this.authService= authService;
        this.authenticationManager= authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDTO> signupPassenger(@RequestBody PassengerSignupRequestDTO passengerSignupRequestDTO){

        PassengerDTO response = authService.signupPassenger(passengerSignupRequestDTO);

        return new ResponseEntity<PassengerDTO>(response, HttpStatus.CREATED) ;
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> siginPassenger(@RequestBody AuthRequestDTO  authRequestDTO, HttpServletResponse response){

//        System.out.println(authRequestDTO.getEmail() + " " + authRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            String jwtToken = jwtService.createToken(authRequestDTO.getEmail());

            ResponseCookie cookie = ResponseCookie.from("JwtToken", jwtToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(7*24*3600)
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return new ResponseEntity<>(AuthResponseDTO.builder().success(true).build(), HttpStatus.OK);

        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
