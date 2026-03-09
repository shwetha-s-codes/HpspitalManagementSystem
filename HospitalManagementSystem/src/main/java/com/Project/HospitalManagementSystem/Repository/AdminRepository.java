package com.Project.HospitalManagementSystem.Repository;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Users,Long> {

}
