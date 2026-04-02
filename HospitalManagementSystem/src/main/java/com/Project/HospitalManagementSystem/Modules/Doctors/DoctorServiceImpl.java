package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.DTO.NotificationPayload;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import com.Project.HospitalManagementSystem.Modules.Notification.NotificationService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepo doctorRepo;
    private final NotificationService notificationService;

    public void requestShiftRestore(String availabilityId, String adminId, Users doctor) {

        Doctor doctor1= doctorRepo.findById(doctor.getUserID()).orElseThrow(()-> new InvalidCredentialsException("User Not found"));
        NotificationPayload payload = new NotificationPayload(
                "Doctor " + doctor1.getFirstName() + " requested shift restore",
                "SHIFT_RESTORE_REQUEST",
                doctor.getUserID(),
                availabilityId,
                null, null, null,
                LocalDateTime.now()
        );
        notificationService.notifyAdmin(adminId, payload);
    }
}
