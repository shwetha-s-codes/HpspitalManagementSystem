package com.Project.HospitalManagementSystem.Modules.AllUsers;

import com.Project.HospitalManagementSystem.Modules.DTO.LoginRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.RegisterRequest;
import jakarta.validation.Valid;

public interface UsersService {


    String registerUser(@Valid RegisterRequest request, String token, Byte roleId);
}
