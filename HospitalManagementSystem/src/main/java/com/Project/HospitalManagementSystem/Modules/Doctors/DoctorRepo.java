package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepo extends JpaRepository<Doctor,String> {
    boolean existsByUser(Users user);

    Optional<Doctor> findByUser(Users user);

    @Query(
            value = "SELECT user_id AS userId, first_name AS firstname, last_name AS lastname, specialization FROM doctor WHERE first_name LIKE :name%",
            nativeQuery = true)
    List<DoctorSearchResponse> searchName(@Param("name") String name);
}
