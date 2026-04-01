package com.Project.HospitalManagementSystem.Security;


import com.Project.HospitalManagementSystem.Modules.AllUsers.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReferenceTokenService {

private final ReferenceTokenRepo referenceTokenRepo;
private final UsersRepo usersRepo;

@Value("${refresh.token.expiration}")
private long expiration;

private final SecureRandom secureRandom=new SecureRandom();

public ReferenceToken createReferenceToken(String userId){
    ReferenceToken referenceToken = ReferenceToken.builder()
            .token(generateOpaqueToken())
            .user(usersRepo.findById(userId)
            .orElseThrow(()->new RuntimeException("User not found")))
            .expiresAt(Instant.now().plusMillis(expiration))
            .revoked(false)
            .build();
    return  referenceTokenRepo.save(referenceToken);
}

public ReferenceToken validateReferenceToken(String token){
    ReferenceToken referenceToken = referenceTokenRepo.findByToken(token).orElseThrow(()->new RuntimeException("Invalid Refresh token"));

    if(referenceToken.isRevoked()){
        throw new RuntimeException(" token revoked");
    }

    if(referenceToken.getExpiresAt().isBefore(Instant.now())){
        throw new RuntimeException(" token expired");
    }
    return referenceToken;
}

public void revokeReferenceToken(String token){
    ReferenceToken referenceToken = referenceTokenRepo.findByToken(token).orElseThrow(()->new RuntimeException("Invalid Refresh Token"));
    referenceToken.setRevoked(true);
    referenceTokenRepo.save(referenceToken);
}

private String generateOpaqueToken(){
    byte[] bytes=new byte[32];
    secureRandom.nextBytes(bytes);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

}

}
