package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="Admins")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="adminid" ,columnDefinition ="VARCHAR(36)")
    private String adminId;

    @Column(nullable = false)
    private  String firstName;

    @Column(nullable = false)
    private  String lastName;


    @Column(nullable = false,unique = true)
    private String emailID;

    @Column(nullable = false)
    private String password;


    //A user can have many roles and vice versa
    @ManyToMany(fetch = FetchType.EAGER)
    //Table containing the foreign keys of User & Roles makes sure that there is no redundant entries
    @JoinTable(
            name="User_Roles",
            joinColumns =  @JoinColumn(name="userID"),
            inverseJoinColumns = @JoinColumn(name="roleID")

    )
    private Set<Roles> roles=new HashSet<>();


    @Column(name="active" , nullable = false,columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean active =true;

    @CreationTimestamp

    private LocalDateTime createdAt;
}
