package com.Project.HospitalManagementSystem.Modules.LookUpTables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    private Byte roleID;

    @Column(nullable = false,unique = true)
    private String name;


}
