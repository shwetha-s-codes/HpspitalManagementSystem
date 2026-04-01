package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.DTO.DoctorAvailabilityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;

public interface DoctorAvailabilityRepo extends JpaRepository<DoctorAvailability,String> {


    @Query(value = """
                SELECT COUNT(*) FROM doctor_availability a
                WHERE a.doctor_id = :doctorId
                  AND a.day = :day
                  AND (a.start_time < :endTime AND a.end_time > :startTime)
                  AND (:availabilityId IS NULL OR a.availability_id != :availabilityId)
            """, nativeQuery = true)
    int hasOverlappingShift(
            @Param("doctorId") String doctorId,
            @Param("day") String day,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            @Param("availabilityId") String availabilityId
    );

    @Query(value = """
SELECT availability_id, day, start_time, end_time 
FROM doctor_availability 
WHERE doctor_id = :doctorId
""",
            countQuery = "SELECT COUNT(*) FROM doctor_availability WHERE doctor_id = :doctorId",
            nativeQuery = true)
    Page<DoctorAvailabilityResponse> findScheduleByDoctorId(
            @Param("doctorId") String doctorId,
            Pageable pageable);

    @Query(value = """
SELECT availability_id, day, start_time, end_time 
FROM doctor_availability 
WHERE doctor_id = :doctorId
AND day = :day
""",
            countQuery = "SELECT COUNT(*) FROM doctor_availability WHERE doctor_id = :doctorId AND day = :day",
            nativeQuery = true)
    Page<DoctorAvailabilityResponse> findScheduleByDoctorIdAndDay(
            @Param("doctorId") String doctorId,
            @Param("day") String day,
            Pageable pageable);

    @Modifying
    @Query(value = "UPDATE doctor_availability SET deleted = true WHERE availability_id = :availabilityId AND doctor_id = :doctorId", nativeQuery = true)
    void softDeleteShift(@Param("availabilityId") String availabilityId, @Param("doctorId") String doctorId);

    // For restore
    @Modifying
    @Query(value = "UPDATE doctor_availability SET deleted = false WHERE availability_id = :availabilityId AND doctor_id = :doctorId", nativeQuery = true)
    void restoreShift(@Param("availabilityId") String availabilityId, @Param("doctorId") String doctorId);

}
