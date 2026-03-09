package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    private  long doctorId;
    @OneToOne
    @MapsId
    @JoinColumn(name="userId")
    private Users user;

    @Column(nullable = false)
    private  String firstName;

    @Column(nullable = false)
    private  String lastName;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false,unique = true)
    private  String phoneNumber;

    @ManyToOne
    @JoinColumn(name="genderId")
    private Gender gender;


    //Appointment ID




}
