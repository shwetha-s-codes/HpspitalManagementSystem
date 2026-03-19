package com.Project.HospitalManagementSystem.Modules.AllUsers;

import com.Project.HospitalManagementSystem.Modules.LookUpTables.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepo extends JpaRepository<Gender,Byte> {

}
