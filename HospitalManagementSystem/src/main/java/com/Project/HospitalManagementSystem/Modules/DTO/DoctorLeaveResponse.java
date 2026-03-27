package com.Project.HospitalManagementSystem.Modules.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public interface DoctorLeaveResponse {
    String getLeaveId();
    LocalDate getLeaveFrom();
    LocalDate getLeaveTo();
    LocalTime getStartTime();
    LocalTime getEndTime();
}
