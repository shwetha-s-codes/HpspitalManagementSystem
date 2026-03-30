package com.Project.HospitalManagementSystem.Security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken,String> {
    Optional<RefreshToken> findByToken(String token);
}
