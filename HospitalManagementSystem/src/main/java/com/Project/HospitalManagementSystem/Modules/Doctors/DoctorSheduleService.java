package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShedule;

import java.util.List;

public interface DoctorSheduleService {
    public String setDoctorShedule(String userId, Byte roleId,DoctorShedule doctorShedule);
}
