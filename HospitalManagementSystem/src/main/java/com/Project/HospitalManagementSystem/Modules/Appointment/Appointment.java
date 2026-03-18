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
@Table(name="Appointments")
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
    private LocalTime appointmenttime;

    @CreationTimestamp
    private LocalDateTime appointmentcreationtimestamp;


}
