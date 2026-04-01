package com.Project.HospitalManagementSystem.Security;

import com.Project.HospitalManagementSystem.Modules.DTO.LoginRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }



    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String refreshTOken){
        authService.logout(refreshTOken);
        return ResponseEntity.ok("Logged out Successfully");
    }
}
