package com.Project.HospitalManagementSystem.Security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReferenceTokenRepo extends JpaRepository<ReferenceToken,String> {
    Optional<ReferenceToken> findByToken(String token);
}
