package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;
import jakarta.validation.Valid;

public interface AdminService {


    String deleteUser(String email);

    public void tokenGeneration(@Valid GenerateTokenRequest request, String adminmail);
}
