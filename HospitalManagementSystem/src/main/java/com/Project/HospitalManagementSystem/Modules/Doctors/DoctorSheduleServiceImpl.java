package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.AllUsers.RolesRepo;
import com.Project.HospitalManagementSystem.Modules.AllUsers.UsersRepo;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShedule;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShift;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorSheduleServiceImpl implements DoctorSheduleService{

    @Autowired
    DoctorAvailabilityRepo doctorAvailabilityRepo;

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    RolesRepo rolesRepo;

    public String setDoctorShedule(String userId, Byte roleId, DoctorShedule doctorShedule){

        Doctor doctor=doctorRepo.findById(userId).orElseThrow(()->new InvalidCredentialsException("Doctor Information does not exists"));
        Roles role= rolesRepo.getByname(roleId).orElseThrow(()-> new InvalidCredentialsException("Role does not exists"));

        if (!"DOCTOR".equalsIgnoreCase(role.getName())) {
            throw new InvalidCredentialsException("You can not access this resource");
        }
        List<DoctorAvailability> availabilities = doctorShedule.getDoctorShifts()
                .stream()
                .map(shift -> {
                    DoctorAvailability availability = new DoctorAvailability();
                    availability.setDoctor(doctor);
                    availability.setDay(shift.getDay());
                    availability.setStartTime(shift.getStartTime());
                    availability.setEndTime(shift.getEndTime());
                    return availability;
                })
                .collect(Collectors.toList());

        doctorAvailabilityRepo.saveAll(availabilities);

        return "Doctor Shedule Updated Successfully";
    }



}
