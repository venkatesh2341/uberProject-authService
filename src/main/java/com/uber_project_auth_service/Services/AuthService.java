package com.uber_project_auth_service.Services;

import com.uber_project_auth_service.DTOs.PassengerDTO;
import com.uber_project_auth_service.DTOs.PassengerSignupRequestDTO;

public interface AuthService {
    public PassengerDTO signupPassenger(PassengerSignupRequestDTO passengerSignupRequestDTO);
}
