package com.uber_project_auth_service.Repositories;

import com.uber_project_auth_service.Models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Passenger, Long> {
}
