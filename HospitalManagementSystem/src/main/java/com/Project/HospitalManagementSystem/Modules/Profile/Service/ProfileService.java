package com.Project.HospitalManagementSystem.Modules.Profile.Service;

import com.Project.HospitalManagementSystem.Modules.DTO.DoctorProfileRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.NurseProfileRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.PatientProfileRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.StaffProfileRequest;

public interface ProfileService {
    String createDoctorProfile(String s, DoctorProfileRequest request);

    String createNurseProfile(String s, NurseProfileRequest request);

    String createPatientProfile(String s, PatientProfileRequest request);

    String createStaffProfile(String s, StaffProfileRequest request);

    String updateDoctorProfile(String s, DoctorProfileRequest request);

    String updateNurseProfile(String s, NurseProfileRequest request);

    String updatePatientProfile(String s, PatientProfileRequest request);

    String updateStaffProfile(String s, StaffProfileRequest request);
}
