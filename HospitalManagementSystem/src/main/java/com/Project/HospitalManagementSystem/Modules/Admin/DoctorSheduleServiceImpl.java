package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.AllUsers.RolesRepo;
import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.AllUsers.UsersRepo;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorAvailabilityResponse;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShedule;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShift;
import com.Project.HospitalManagementSystem.Modules.Doctors.Doctor;
import com.Project.HospitalManagementSystem.Modules.Doctors.DoctorAvailability;
import com.Project.HospitalManagementSystem.Modules.Doctors.DoctorAvailabilityRepo;
import com.Project.HospitalManagementSystem.Modules.Doctors.DoctorRepo;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import com.Project.HospitalManagementSystem.Modules.Exceptions.ShiftOverLapException;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DoctorSheduleServiceImpl implements DoctorSheduleService{

    @Autowired
    DoctorAvailabilityRepo doctorAvailabilityRepo;

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    RolesRepo rolesRepo;

    @Autowired
    UsersRepo usersRepo;

    @Transactional
    public String setDoctorShedule(String userId, DoctorShedule doctorShedule){

        Doctor doctor=validateAndGetDoctor(userId);
        List<DoctorAvailability>availabilities= doctorShedule.getDoctorShifts()
                .stream()
                .map(shift-> buildandValidateShift(doctor,shift,null))
                .collect(Collectors.toList());


        doctorAvailabilityRepo.saveAll(availabilities);
        return "Shedule Inserted Successfully";



    }
    @Transactional
    public String addShift(String userId,DoctorShift shift) {

        Doctor doctor = validateAndGetDoctor(userId);
        DoctorAvailability availability = buildandValidateShift(doctor, shift, null);
        doctorAvailabilityRepo.save(availability);
        return "Shift added successfully";
    }

    @Transactional
    public String updateShift(String userId, String shiftId, DoctorShift shift) {

        Doctor doctor = validateAndGetDoctor(userId);

        DoctorAvailability existing = doctorAvailabilityRepo.findById(shiftId)
                .orElseThrow(() -> new RuntimeException("Shift not found"));
        System.out.println(shift.getStartTime());
        System.out.println(shift.getEndTime());

        buildandValidateShift(doctor, shift, shiftId);

        existing.setDay(shift.getDay());
        existing.setStartTime(shift.getStartTime());
        existing.setEndTime(shift.getEndTime());

        doctorAvailabilityRepo.save(existing);
        return "Shift updated successfully";
    }

    @Transactional
    public void deleteShift(String availabilityId, String doctorId) {
        validateAndGetDoctor(doctorId);
        doctorAvailabilityRepo.softDeleteShift(availabilityId, doctorId);
        // placeholder for notification
        notifyDoctor(doctorId, "Your shift has been removed by admin");
    }

    @Transactional
    public void restoreShift(String availabilityId, String doctorId) {
        validateAndGetDoctor(doctorId);
        doctorAvailabilityRepo.restoreShift(availabilityId, doctorId);
        // placeholder for notification
        notifyDoctor(doctorId, "Your shift has been restored by admin");
    }

    private void notifyDoctor(String doctorId, String message) {
        // TODO: implement notification layer
        log.info("Notification to doctor {}: {}", doctorId, message);
    }

    @Transactional
    public Page<DoctorAvailabilityResponse> showShift(Users users, String day, String doctorId,Pageable pageable) {
        String userId= isAdmin() ? (doctorId != null ? doctorId : users.getUserID()) : users.getUserID();
        Doctor doctor = validateAndGetDoctor(userId);

        if (day == null)
            return doctorAvailabilityRepo.findScheduleByDoctorId(doctor.getDoctorId(), pageable);

        else
            return doctorAvailabilityRepo.findScheduleByDoctorIdAndDay(doctor.getDoctorId(), day, pageable);

    }




    //Helper Methods

    private Doctor validateAndGetDoctor(String userId){
       log.info(userId);
       Users user= usersRepo.findById(userId).orElseThrow(()-> new InvalidCredentialsException("user not found"));
       Byte roleId=user.getRoles().stream().map(Roles::getRoleID).findFirst().orElseThrow(()->new InvalidCredentialsException("No role found for this user"));
       log.info(String.valueOf(roleId));

        Roles role =rolesRepo.findById(roleId).orElseThrow(()-> new InvalidCredentialsException("Role Not found"));
        if (!"DOCTOR".equalsIgnoreCase(role.getName())) {
            throw new InvalidCredentialsException("You can not access this resource");
        }
        Doctor doctor=doctorRepo.findById(userId).orElseThrow(()->new InvalidCredentialsException("Your Info not found in Doctor Repo"));
        return doctor;
    }

    private DoctorAvailability buildandValidateShift(Doctor doctor,DoctorShift shift,String availabilityId){

        System.out.println(shift.getStartTime());
        System.out.println(shift.getEndTime());
        //Handling the timings are according to real world scenario
        if (shift.getEndTime().isBefore(shift.getStartTime()) ||
                shift.getEndTime().equals(shift.getStartTime())) {
            throw new ShiftOverLapException("End time must be after start time");
        }

        boolean overlaps = doctorAvailabilityRepo.hasOverlappingShift(
                doctor.getDoctorId(),
                shift.getDay().name(),
                shift.getStartTime(),
                shift.getEndTime(),
                availabilityId

        )>0;

        if (overlaps) {
            throw new ShiftOverLapException(
                    "Shift overlaps with an existing shift on " + shift.getDay()
            );
        }

        DoctorAvailability availability = new DoctorAvailability();
        availability.setDoctor(doctor);
        availability.setDay(shift.getDay());
        availability.setStartTime(shift.getStartTime());
        availability.setEndTime(shift.getEndTime());
        return availability;
    }
    private boolean isAdmin() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("Admin"));
    }


    }












