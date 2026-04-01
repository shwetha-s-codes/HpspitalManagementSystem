package com.Project.HospitalManagementSystem.Modules.DTO;

import java.time.LocalTime;

public interface DoctorAvailabilityResponse {


    String getAvailabilityId();
    String getDay();
    LocalTime getStartTime();
    LocalTime getEndTime();



}
