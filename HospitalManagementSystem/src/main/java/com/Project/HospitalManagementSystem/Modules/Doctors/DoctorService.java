package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.DTO.RegisterRequest;
import jakarta.validation.Valid;

public interface DoctorService {



    String registerDoctor(@Valid RegisterRequest request);
}
