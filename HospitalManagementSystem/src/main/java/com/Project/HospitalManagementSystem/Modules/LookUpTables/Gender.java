package com.Project.HospitalManagementSystem.Modules.LookUpTables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gender {

    @Id
    private  Byte genderId;

    @Column(nullable = false)
    private  String genderCode;
}
