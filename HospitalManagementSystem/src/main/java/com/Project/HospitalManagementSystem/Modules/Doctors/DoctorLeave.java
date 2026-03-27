package com.Project.HospitalManagementSystem.Modules.Doctors;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "DoctorLeave")
@AllArgsConstructor
@NoArgsConstructor
public class DoctorLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String leaveId;

    @ManyToOne
    @JoinColumn(name = "doctorId", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDate leaveFrom;

    @Column(nullable = false)
    private LocalDate leaveTo;

    private LocalTime startTime;

    private LocalTime endTime;
}