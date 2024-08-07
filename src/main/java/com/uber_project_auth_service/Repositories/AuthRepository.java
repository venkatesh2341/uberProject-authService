package com.uber_project_auth_service.Repositories;


import com.uber_project.entity_provider.Models.Driver;
import com.uber_project.entity_provider.Models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Passenger, Long> {

    Optional<Passenger> findPassengerByEmail(String email);

}
