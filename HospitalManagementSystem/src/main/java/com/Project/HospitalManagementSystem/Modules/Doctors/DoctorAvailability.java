package com.Project.HospitalManagementSystem.Modules.Doctors;


import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity

@Table(name="DoctorAvailability",uniqueConstraints =@UniqueConstraint(columnNames = {"doctorId","day","startTime","endTime"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String availabilityId;

    @ManyToOne
    @JoinColumn(name = "doctorId", nullable = false)
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek day;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    private boolean deleted=false;
}



