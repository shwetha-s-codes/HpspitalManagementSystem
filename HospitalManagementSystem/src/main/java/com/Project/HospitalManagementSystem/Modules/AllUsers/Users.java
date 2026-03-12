package com.Project.HospitalManagementSystem.Modules.AllUsers;

import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(nullable = false,unique = true)
    private String emailId;

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
    
    private boolean active =true;

    @CreationTimestamp

    private LocalDateTime createdAt;


}
