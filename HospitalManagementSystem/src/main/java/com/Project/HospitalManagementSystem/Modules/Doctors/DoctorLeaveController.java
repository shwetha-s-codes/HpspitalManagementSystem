package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.DTO.DoctorLeaveRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorLeaveResponse;
import com.Project.HospitalManagementSystem.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorLeaveController {

    private final DoctorLeaveService doctorLeaveService;
    private final JwtService jwtService;

    @PostMapping("/leave")
    public ResponseEntity<DoctorLeaveResponse> applyLeave(
            @RequestHeader("Authorization") String token,
            @RequestBody DoctorLeaveRequest request) {

        String doctorId = jwtService.extractUserId(token);


        return ResponseEntity.ok(
                doctorLeaveService.applyLeave(doctorId,request)
        );
    }
}
