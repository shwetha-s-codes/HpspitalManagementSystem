package com.Project.HospitalManagementSystem.Repository;

import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepo extends JpaRepository<Roles,Long> {

    Optional<Roles> findByRoleName(String roleName);
}
