package com.Project.HospitalManagementSystem.Modules.Profile.Controller;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorProfileRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.NurseProfileRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.PatientProfileRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.StaffProfileRequest;
import com.Project.HospitalManagementSystem.Modules.Profile.Service.ProfileService;
import com.Project.HospitalManagementSystem.Security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
                                      @AuthenticationPrincipal Users users) {
        return profileService.createDoctorProfile(users.getUserID(), request);
    }

    @PostMapping("/create/nurse")
    public String createNurseProfile(@RequestBody NurseProfileRequest request,
                                     @AuthenticationPrincipal Users users) {
        return profileService.createNurseProfile(users.getUserID(), request);
    }

    @PostMapping("/create/patient")
    public String createPatientProfile(@RequestBody PatientProfileRequest request,
                                       @AuthenticationPrincipal Users users) {
        return profileService.createPatientProfile(users.getUserID(), request);
    }

    @PostMapping("/create/staff")
    public String createStaffProfile(@RequestBody StaffProfileRequest request,
                                     @AuthenticationPrincipal Users users) {
        return profileService.createStaffProfile(users.getUserID(), request);
    }



    @PutMapping("/update/doctor")
    public String updateDoctorProfile(@RequestBody DoctorProfileRequest request,
                                      @AuthenticationPrincipal Users users) {
        return profileService.updateDoctorProfile(users.getUserID(), request);
    }

    @PutMapping("/update/nurse")
    public String updateNurseProfile(@RequestBody NurseProfileRequest request,
                                     @AuthenticationPrincipal Users users) {
        return profileService.updateNurseProfile(users.getUserID(), request);
    }

    @PutMapping("/update/patient")
    public String updatePatientProfile(@RequestBody PatientProfileRequest request,
                                       @AuthenticationPrincipal Users users) {
        return profileService.updatePatientProfile(users.getUserID(), request);
    }

    @PutMapping("/update/staff")
    public String updateStaffProfile(@RequestBody StaffProfileRequest request,
                                     @AuthenticationPrincipal Users users) {
        return profileService.updateStaffProfile(users.getUserID(), request);
    }



}