package com.Project.HospitalManagementSystem.Modules.Patients;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.Appointment.Appointment;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="Patients")
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @Column(columnDefinition ="VARCHAR(36)")
    private  String patientId;

    @OneToOne
    @MapsId //Used to access primary key from Parent table Users
    @JoinColumn(name="userId")
    private Users user;

    @Column(nullable = false)
    private  String firstName;

    @Column(nullable = false)
    private  String lastName;

    @Column(nullable = false)
    private byte age;

    @ManyToOne
    @JoinColumn(name="genderId")
    private Gender gender;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false,unique = true)
    private  String phoneNumber;

    @OneToMany
    @JoinColumn(name="appointmentId")
    private List<Appointment> appointment=new ArrayList<>();


}
