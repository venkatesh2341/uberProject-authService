package com.uber_project_auth_service.Repositories;

import com.uber_project.entity_provider.Models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    Optional<Driver> findDriverByAadhaar(String aadhaar);
}
