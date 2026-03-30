package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.DTO.DoctorLeaveRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorLeaveResponse;

public interface DoctorLeaveService {
    public DoctorLeaveResponse applyLeave(
            String userId,DoctorLeaveRequest request);
}
