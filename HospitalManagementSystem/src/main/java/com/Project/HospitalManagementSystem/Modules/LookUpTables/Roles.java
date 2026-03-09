package com.Project.HospitalManagementSystem.Modules.LookUpTables;

import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class Roles {
    @Id
    private Byte roleID;

    @Column(nullable = false,unique = true)
    private String name;


}
