package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.AllUsers.RolesRepo;
import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.AllUsers.UsersRepo;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorAvailabilityResponse;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShedule;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShift;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import com.Project.HospitalManagementSystem.Modules.Exceptions.ShiftOverLapException;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

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
    public String deleteShift(String userId,String shiftId) {

        validateAndGetDoctor(userId);

        if (!doctorAvailabilityRepo.existsById(shiftId)) {
            throw new ShiftOverLapException("Shift not found");
        }

        doctorAvailabilityRepo.deleteById(shiftId);
        return "Shift deleted successfully";
    }

    @Transactional
    public List<DoctorAvailabilityResponse>  showShift(String userId, String day){



        Doctor doctor = validateAndGetDoctor(userId);
        if(day==null)
        return doctorAvailabilityRepo.findScheduleByDoctorId(doctor.getDoctorId());
        else
            return doctorAvailabilityRepo.findScheduleByDoctorIdAndDay(doctor.getDoctorId(),day);

    }




    //Helper Methods

    private Doctor validateAndGetDoctor(String userId){

       Users user= usersRepo.findById(userId).orElseThrow(()-> new InvalidCredentialsException("user not found"));
       Byte roleId=user.getRoles().stream().map(Roles::getRoleID).findFirst().orElseThrow(()->new InvalidCredentialsException("No role found for this user"));


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


    }












