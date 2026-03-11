package com.Project.HospitalManagementSystem.Repository;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {

    boolean existsByEmailID(String emailID);


}
