package com.Project.HospitalManagementSystem.Security;


import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import jakarta.persistence.*;
import org.hibernate.query.NativeQuery;

import java.time.Instant;

@Entity
@Table(name="RefreshToken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",columnDefinition = "VARCHAR(36)")
    private String tokenId;

    @Column(nullable = false,unique = true)
    private String token;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId")
    private Users user;

    @Column(nullable = false)
    private Instant expiresAt;

    private boolean revoked;

}
