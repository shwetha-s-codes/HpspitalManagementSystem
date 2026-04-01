package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorLeaveRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorLeaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorLeaveController {

    private final DoctorLeaveService doctorLeaveService;


    @PostMapping("/leave")
    public ResponseEntity<DoctorLeaveResponse> applyLeave(
            @AuthenticationPrincipal Users users,
            @RequestBody DoctorLeaveRequest request) {

        String doctorId= users.getUserID();


        return ResponseEntity.ok(
                doctorLeaveService.applyLeave(doctorId,request)
        );
    }
}
