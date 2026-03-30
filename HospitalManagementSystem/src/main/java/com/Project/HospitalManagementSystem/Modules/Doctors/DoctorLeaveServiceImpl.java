package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.AllUsers.RolesRepo;
import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.AllUsers.UsersRepo;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorLeaveRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorLeaveResponse;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorLeaveServiceImpl implements DoctorLeaveService {

    private final DoctorLeaveRepo doctorLeaveRepo;
    private final DoctorRepo doctorRepo;
    private final UsersRepo usersRepo;
    private final RolesRepo rolesRepo;

    @Transactional
    public DoctorLeaveResponse applyLeave(
            String userId, DoctorLeaveRequest request) {
        log.info(userId);

        Doctor doctor = validateAndGetDoctor(userId);

        // validate date range
        if (request.getLeaveTo().isBefore(request.getLeaveFrom())) {
            throw new RuntimeException("Leave end date must be after start date");
        }

        // validate half day timings if provided
        if (request.getStartTime() != null && request.getEndTime() != null) {
            if (request.getEndTime().isBefore(request.getStartTime())) {
                throw new RuntimeException("End time must be after start time");
            }
        }

        // check overlapping leaves
        if (doctorLeaveRepo.hasOverlappingLeave(
                doctor.getDoctorId(),
                request.getLeaveFrom(),
                request.getLeaveTo())) {
            throw new RuntimeException(
                    "Leave overlaps with an existing leave application"
            );
        }

        // build and save
        DoctorLeave leave = new DoctorLeave();
        leave.setDoctor(doctor);
        leave.setLeaveFrom(request.getLeaveFrom());
        leave.setLeaveTo(request.getLeaveTo());
        leave.setStartTime(request.getStartTime());
        leave.setEndTime(request.getEndTime());

        DoctorLeave saved = doctorLeaveRepo.save(leave);

        return doctorLeaveRepo.findById(saved.getLeaveId())
                .map(l -> (DoctorLeaveResponse) new DoctorLeaveResponse() {
                    public String getLeaveId() { return l.getLeaveId(); }
                    public LocalDate getLeaveFrom() { return l.getLeaveFrom(); }
                    public LocalDate getLeaveTo() { return l.getLeaveTo(); }
                    public LocalTime getStartTime() { return l.getStartTime(); }
                    public LocalTime getEndTime() { return l.getEndTime(); }
                })
                .orElseThrow(() -> new RuntimeException("Leave not found after saving"));
    }

    // ─── private helper ─────────────────────────────────────────────

    private Doctor validateAndGetDoctor(String userId) {
        Users user= usersRepo.findById(userId).orElseThrow(()-> new InvalidCredentialsException("user not found"));
        Byte roleId=user.getRoles().stream().map(Roles::getRoleID).findFirst().orElseThrow(()->new InvalidCredentialsException("No role found for this user"));
        Roles role = rolesRepo.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role does not exist"));
        if (!"DOCTOR".equalsIgnoreCase(role.getName())) {
            throw new RuntimeException("You cannot access this resource");
        }
        return doctorRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
}
