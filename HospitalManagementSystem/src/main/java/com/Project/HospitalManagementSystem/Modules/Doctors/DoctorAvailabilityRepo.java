package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.DTO.DoctorAvailabilityResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

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
    SELECT a.availability_id, a.day, a.start_time, a.end_time 
    FROM doctor_availability a
    WHERE a.doctor_id = :doctorId
    """, nativeQuery = true)

    List<DoctorAvailabilityResponse> findScheduleByDoctorId(@Param("doctorId") String doctorId);

    @Query(value = """
    SELECT availability_id, day, start_time, end_time 
    FROM doctor_availability 
    WHERE doctor_id = :doctorId
    AND day = :day
    """, nativeQuery = true)
    List<DoctorAvailabilityResponse> findScheduleByDoctorIdAndDay(
            @Param("doctorId") String doctorId,
            @Param("day") String day
    );

}