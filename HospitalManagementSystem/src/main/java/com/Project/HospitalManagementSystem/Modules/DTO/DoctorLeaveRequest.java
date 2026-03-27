package com.Project.HospitalManagementSystem.Modules.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorLeaveRequest {
    private LocalDate leaveFrom;
    private LocalDate leaveTo;
    private LocalTime startTime;
    private LocalTime endTime;
}
