package com.Project.HospitalManagementSystem.Modules.Appointment;

import com.Project.HospitalManagementSystem.Modules.Doctors.Doctor;
import com.Project.HospitalManagementSystem.Modules.Patients.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name="Appointments",uniqueConstraints = @UniqueConstraint(columnNames = {"doctorId","appointmentdate","startTime"}))
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String appointmentId;

    @ManyToOne
    @JoinColumn(name ="doctorId",nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name ="patientId",nullable = false)
    private Patient  patient;

    @Column(nullable = false)
    private LocalDate appointmentdate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable=false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status=AppointmentStatus.OPEN;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime appointmentcreationtimestamp=LocalDateTime.now();


}
