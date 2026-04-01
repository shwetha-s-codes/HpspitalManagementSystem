package com.Project.HospitalManagementSystem.Modules.Shared;


import com.Project.HospitalManagementSystem.Modules.Admin.DoctorSheduleService;
import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorAvailabilityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class SheduleController {

    private final DoctorSheduleService doctorSheduleService;

    @GetMapping("/schedule")
    @PreAuthorize("hasAnyAuthority('Admin', 'Doctor')")
    public ResponseEntity<Page<DoctorAvailabilityResponse>> getShift(
            @AuthenticationPrincipal Users users,
            @RequestParam(required = false) String day,
            @RequestParam(required = false) String doctorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info(doctorId);

        Pageable pageable = PageRequest.of(page, size);



        return ResponseEntity.ok(doctorSheduleService.showShift(users, day, doctorId,pageable));
    }
}
