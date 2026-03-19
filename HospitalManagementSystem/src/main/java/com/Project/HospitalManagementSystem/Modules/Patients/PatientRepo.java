package com.Project.HospitalManagementSystem.Modules.Patients;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient,String> {
    boolean existsByUser(Users user);

    Optional<Patient > findByUser(Users user);
}
