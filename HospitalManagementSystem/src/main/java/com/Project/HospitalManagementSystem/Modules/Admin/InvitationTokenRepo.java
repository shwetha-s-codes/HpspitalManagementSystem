package com.Project.HospitalManagementSystem.Modules.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvitationTokenRepo extends JpaRepository<InvitationToken,String> {
    Optional<InvitationToken> findBytoken(String token);
}
