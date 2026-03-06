package com.Project.HospitalManagementSystem.UsersProfile.Users;

import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleID;

    @Column(nullable = false,unique = true)
    private String name;


}
