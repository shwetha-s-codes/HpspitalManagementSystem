package com.Project.HospitalManagementSystem.Security;


import com.Project.HospitalManagementSystem.Modules.AllUsers.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

private final RefreshTokenRepo refreshTokenRepo;
private final UsersRepo usersRepo;

@Value("${refresh.token.expiration}")
private long expiration;

public RefreshToken createRefreshToken(String userId){
    RefreshToken refreshToken= RefreshToken.builder()
            .token(UUID.randomUUID().toString())
            .user(usersRepo.findById(userId)
            .orElseThrow(()->new RuntimeException("User not found")))
            .expiresAt(Instant.now().plusMillis(expiration))
            .revoked(false)
            .build();
    return  refreshTokenRepo.save(refreshToken);
}

public RefreshToken validateRefreshToken(String token){
    RefreshToken refreshToken=refreshTokenRepo.findByToken(token).orElseThrow(()->new RuntimeException("Invalid Refresh token"));

    if(refreshToken.isRevoked()){
        throw new RuntimeException("Refresh token revoked");
    }

    if(refreshToken.getExpiresAt().isBefore(Instant.now())){
        throw new RuntimeException("Refresh token expired");
    }
    return refreshToken;
}

public void revokeRefreshToken(String token){
    RefreshToken refreshToken=refreshTokenRepo.findByToken(token).orElseThrow(()->new RuntimeException("Invalid Refresh Token"));
    refreshToken.setRevoked(true);
    refreshTokenRepo.save(refreshToken);
}

public RefreshToken rotateRefreshToken(String oldToken){
    RefreshToken old=validateRefreshToken(oldToken);
    old.setRevoked(true);
    refreshTokenRepo.save(old);
    return createRefreshToken(old.getUser().getUserID());

}

}
