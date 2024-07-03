package com.uber_project_auth_service.Services.Impl;


import com.uber_project.entity_provider.Models.Passenger;
import com.uber_project_auth_service.Repositories.AuthRepository;
import com.uber_project_auth_service.helpers.AuthPassengerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private  AuthRepository authRepository;



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Passenger> passenger= authRepository.findPassengerByEmail(userName);
        if(passenger.isPresent())
        {
            return new AuthPassengerDetails(passenger.get());
        }
        else{
            throw new UsernameNotFoundException("Passenger with given username not found");
        }

    }
}
