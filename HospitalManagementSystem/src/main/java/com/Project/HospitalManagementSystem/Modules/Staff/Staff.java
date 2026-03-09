package com.Project.HospitalManagementSystem.Modules.Staff;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import jakarta.persistence.*;

public class Staff {
    @Id
    private  long staffId;

    @OneToOne
    @MapsId //Used to access primary key from Parent table Users
    @JoinColumn(name="userId")
    private Users user;

    @Column(nullable = false)
    private  String firstName;

    @Column(nullable = false)
    private  String lastName;

    @OneToMany
    @JoinColumn(name="genderId")
    private Byte genderId;

    @Column(nullable = false,unique = true)
    private  String phoneNumber;
}
