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
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request){

        Users user= usersRepo.findByemailId(request.getEmailId())
                .orElseThrow(()->new RuntimeException("User not found"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUserID(),
                        request.getPassword()
                )
        );

        String accessToken=jwtService.generateToken(user.getUserID());

        RefreshToken refreshToken=refreshTokenService.createRefreshToken(user.getUserID());
        return new LoginResponse(accessToken,refreshToken.getToken());
    }

    public LoginResponse refresh(String refreshToken){
        RefreshToken newRefreshToken=refreshTokenService.rotateRefreshToken(refreshToken);
        String accessToken =jwtService.generateToken(newRefreshToken.getUser().getUserID());
        return new LoginResponse(accessToken,newRefreshToken.getToken());
    }

    public void logout(String refreshToken){
        refreshTokenService.revokeRefreshToken(refreshToken);
    }
}
