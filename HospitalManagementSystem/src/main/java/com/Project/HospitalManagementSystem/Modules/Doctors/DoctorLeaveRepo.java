package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.DTO.DoctorLeaveResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DoctorLeaveRepo extends JpaRepository<DoctorLeave, String> {

    @Query(value = """
        SELECT COUNT(*) > 0 
        FROM doctor_leave
        WHERE doctor_id = :doctorId
        AND leave_from <= :leaveTo
        AND leave_to >= :leaveFrom
        """, nativeQuery = true)
    int  hasOverlappingLeave(
            @Param("doctorId") String doctorId,
            @Param("leaveFrom") LocalDate leaveFrom,
            @Param("leaveTo") LocalDate leaveTo
    );

    @Query(value = """
        SELECT leave_id, leave_from, leave_to, start_time, end_time
        FROM doctor_leaves
        WHERE doctor_id = :doctorId
        """, nativeQuery = true)
    List<DoctorLeaveResponse> findLeavesByDoctorId(
            @Param("doctorId") String doctorId
    );
}
