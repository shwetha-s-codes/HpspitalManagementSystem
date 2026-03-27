package com.Project.HospitalManagementSystem.Modules.Profile.Controller;

import com.Project.HospitalManagementSystem.Modules.DTO.DoctorProfileRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.NurseProfileRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.PatientProfileRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.StaffProfileRequest;
import com.Project.HospitalManagementSystem.Modules.Profile.Service.ProfileService;
import com.Project.HospitalManagementSystem.Security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private JwtService jwtService;



    @PostMapping("/create/doctor")
    public String createDoctorProfile(@RequestBody DoctorProfileRequest request,
                                      HttpServletRequest httpRequest) {
        return profileService.createDoctorProfile(extractEmail(httpRequest), request);
    }

    @PostMapping("/create/nurse")
    public String createNurseProfile(@RequestBody NurseProfileRequest request,
                                     HttpServletRequest httpRequest) {
        return profileService.createNurseProfile(extractEmail(httpRequest), request);
    }

    @PostMapping("/create/patient")
    public String createPatientProfile(@RequestBody PatientProfileRequest request,
                                       HttpServletRequest httpRequest) {
        return profileService.createPatientProfile(extractEmail(httpRequest), request);
    }

    @PostMapping("/create/staff")
    public String createStaffProfile(@RequestBody StaffProfileRequest request,
                                     HttpServletRequest httpRequest) {
        return profileService.createStaffProfile(extractEmail(httpRequest), request);
    }



    @PutMapping("/update/doctor")
    public String updateDoctorProfile(@RequestBody DoctorProfileRequest request,
                                      HttpServletRequest httpRequest) {
        return profileService.updateDoctorProfile(extractEmail(httpRequest), request);
    }

    @PutMapping("/update/nurse")
    public String updateNurseProfile(@RequestBody NurseProfileRequest request,
                                     HttpServletRequest httpRequest) {
        return profileService.updateNurseProfile(extractEmail(httpRequest), request);
    }

    @PutMapping("/update/patient")
    public String updatePatientProfile(@RequestBody PatientProfileRequest request,
                                       HttpServletRequest httpRequest) {
        return profileService.updatePatientProfile(extractEmail(httpRequest), request);
    }

    @PutMapping("/update/staff")
    public String updateStaffProfile(@RequestBody StaffProfileRequest request,
                                     HttpServletRequest httpRequest) {
        return profileService.updateStaffProfile(extractEmail(httpRequest), request);
    }


    private String extractEmail(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return jwtService.extractEmail(token);
    }
}