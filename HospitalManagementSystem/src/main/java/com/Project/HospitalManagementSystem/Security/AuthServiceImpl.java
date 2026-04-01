package com.Project.HospitalManagementSystem.Security;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.AllUsers.UsersRepo;
import com.Project.HospitalManagementSystem.Modules.DTO.LoginRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements  AuthService{
    private  final UsersRepo usersRepo;
    private final JwtService jwtService;
    private final ReferenceTokenService referenceTokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailId(),
                        request.getPassword()
                )
        );

        Users user= usersRepo.findByemailId(request.getEmailId())
                .orElseThrow(()->new RuntimeException("User not found"));

        ReferenceToken referenceToken = referenceTokenService.createReferenceToken(user.getUserID());
        return new LoginResponse(referenceToken.getToken());
    }



    public void logout(String token){
        referenceTokenService.revokeReferenceToken(token);
    }
}
