package com.Project.HospitalManagementSystem.Modules.LookUpTables;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="AppointmentStatus")

public class AppointmentStatus {
    @Id
    private Long appointmentId;

    private String status;
}
