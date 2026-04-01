package com.Project.HospitalManagementSystem.Security;


import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="ReferenceToken")
public class ReferenceToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",columnDefinition = "VARCHAR(36)")
    private String tokenId;

    @Column(nullable = false,unique = true)
    private String token;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="userId",nullable = false)
    private Users user;

    @Column(nullable = false)
    private Instant expiresAt;

    private boolean revoked;

}
