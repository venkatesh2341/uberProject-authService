package com.uber_project_auth_service.Services.Impl;

import com.uber_project_auth_service.DTOs.PassengerDTO;
import com.uber_project_auth_service.DTOs.PassengerSignupRequestDTO;
import com.uber_project_auth_service.Models.Passenger;
import com.uber_project_auth_service.Repositories.AuthRepository;
import com.uber_project_auth_service.Services.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    AuthServiceImpl(AuthRepository authRepository, BCryptPasswordEncoder bCryptPasswordEncoder ){
        this.authRepository = authRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public PassengerDTO signupPassenger(PassengerSignupRequestDTO passengerSignupRequestDTO) {

        Passenger passenger = Passenger.builder()
                .email(passengerSignupRequestDTO.getEmail())
                .name(passengerSignupRequestDTO.getName())
                .password(bCryptPasswordEncoder.encode( passengerSignupRequestDTO.getPassword() ))
                .phoneNumber(passengerSignupRequestDTO.getPhoneNumber())
                .build();

        Passenger newPassenger= authRepository.save(passenger);

        return PassengerDTO.passengerDtoFrom(newPassenger);
    }
}
