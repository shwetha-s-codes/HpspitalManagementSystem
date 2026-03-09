package com.Project.HospitalManagementSystem.Modules.Staff;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name="genderId")
    private Gender gender;

    @Column(nullable = false,unique = true)
    private  String phoneNumber;
}
