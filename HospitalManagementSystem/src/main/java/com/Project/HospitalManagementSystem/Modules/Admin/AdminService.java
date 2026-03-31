package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.DTO.DoctorSearchResponse;
import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface AdminService {


    String deleteUser(String email);

    public void tokenGeneration(@Valid GenerateTokenRequest request, String adminId);
    public List<DoctorSearchResponse> searchDoctor(String name);
}
