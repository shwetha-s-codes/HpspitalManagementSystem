package com.Project.HospitalManagementSystem.Modules.Admin;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="invitation_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tokenId;

    @Column(nullable = false,unique=true)
    private String token;

    @Column(nullable = false,unique=true)
    private String roleId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="adminId",nullable = false)
    private  Admins adminId;

   @Column(nullable = false,updatable = false)
   private LocalDateTime createdAt=LocalDateTime.now();

   @Column(nullable = false)
    private LocalDateTime expiresAt;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
    private TokenStatus status=TokenStatus.ACTIVE;
}



