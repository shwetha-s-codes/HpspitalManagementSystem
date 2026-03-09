package com.Project.HospitalManagementSystem.Modules.Nurses;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Gender;
import jakarta.persistence.*;

public class Nurse {

    @Id
    private  long nurseId;

    @OneToOne
    @MapsId //Used to access primary key from Parent table Users
    @JoinColumn(name="userId")
    private Users user;

    @Column(nullable = false)
    private  String firstName;

    @Column(nullable = false)
    private  String lastName;

    @ManyToOne
    @JoinColumn(name="genderId")
    private Gender gender ;


    @Column(nullable = false,unique = true)
    private  String phoneNumber;
}
