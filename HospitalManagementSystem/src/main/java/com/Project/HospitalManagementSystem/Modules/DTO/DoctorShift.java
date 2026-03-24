package com.Project.HospitalManagementSystem.Modules.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorShift {
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

}
