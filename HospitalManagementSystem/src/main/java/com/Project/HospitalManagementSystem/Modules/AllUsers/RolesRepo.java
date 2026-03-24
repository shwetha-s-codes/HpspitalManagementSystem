package com.Project.HospitalManagementSystem.Modules.AllUsers;

import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.BitSet;
import java.util.Optional;

public interface RolesRepo extends JpaRepository<Roles,Byte> {


    Optional<Roles> findByname(String role);

    Optional<Roles> getByname(Byte roleId);
}
