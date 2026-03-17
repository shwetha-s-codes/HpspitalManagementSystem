package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;

public interface AdminService {

    public String tokenGeneration(GenerateTokenRequest request);
}
