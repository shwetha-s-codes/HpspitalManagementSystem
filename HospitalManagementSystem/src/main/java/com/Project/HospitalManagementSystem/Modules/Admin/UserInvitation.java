package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;

public interface UserInvitation {
    String  generateToken(GenerateTokenRequest request);
}
