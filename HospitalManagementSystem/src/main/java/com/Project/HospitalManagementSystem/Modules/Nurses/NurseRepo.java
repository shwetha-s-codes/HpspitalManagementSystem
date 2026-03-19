package com.Project.HospitalManagementSystem.Modules.Nurses;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface NurseRepo extends JpaRepository<Nurse,String> {
    boolean existsByUser(Users user);

    Optional<Nurse> findByUser(Users user);
}
