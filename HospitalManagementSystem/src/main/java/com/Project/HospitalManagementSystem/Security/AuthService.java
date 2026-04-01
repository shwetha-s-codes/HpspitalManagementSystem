package com.Project.HospitalManagementSystem.Security;

import com.Project.HospitalManagementSystem.Modules.DTO.LoginRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.LoginResponse;

public interface AuthService {
    public LoginResponse login(LoginRequest request);

    public void logout(String token);
}
