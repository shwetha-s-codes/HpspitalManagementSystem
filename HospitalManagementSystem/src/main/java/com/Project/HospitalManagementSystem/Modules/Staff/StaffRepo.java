package com.Project.HospitalManagementSystem.Modules.Staff;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepo extends JpaRepository<Staff,String> {
    boolean existsByUser(Users user);

    Optional<Staff> findByUser(Users user);
}
