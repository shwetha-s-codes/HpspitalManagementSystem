package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.Notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final NotificationService notificationService;
    private final DoctorService doctorService;
    @GetMapping("/notifications/subscribe")
    @PreAuthorize("hasAuthority('Doctor')")
    public SseEmitter subscribe(@AuthenticationPrincipal Users users) {
        return notificationService.subscribe(users.getUserID());
    }
    @PostMapping("/shifts/{availabilityId}/restore-request")
    @PreAuthorize("hasAuthority('Doctor')")
    public ResponseEntity<String> requestShiftRestore(
            @PathVariable String availabilityId,
            @RequestParam String adminId,
            @AuthenticationPrincipal Users users) {
        doctorService.requestShiftRestore(availabilityId, adminId, users);
        return ResponseEntity.ok("Restore request sent to admin successfully");
    }
}
