package com.Project.HospitalManagementSystem.UsersProfile.Users;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;
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
    private boolean isActive =true;

    @CreationTimestamp

    private LocalDateTime createdAt;


}
