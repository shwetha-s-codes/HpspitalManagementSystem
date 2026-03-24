package com.Project.HospitalManagementSystem.Modules.AllUsers;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users,String > {

    boolean existsByEmailId(String emailId);

    Optional<Users> findByemailId(String emailId);


}
