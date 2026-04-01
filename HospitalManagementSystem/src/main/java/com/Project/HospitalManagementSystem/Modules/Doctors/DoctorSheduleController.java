package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorAvailabilityResponse;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShedule;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorSheduleController {

    @Autowired
    DoctorSheduleService doctorSheduleService;



    @PostMapping("/shedule/create")
    ResponseEntity<String> createShedule(@AuthenticationPrincipal Users users,
                                   @RequestBody DoctorShedule doctorShedule){


        String doctorId= users.getUserID();


        return ResponseEntity.ok(doctorSheduleService.setDoctorShedule(doctorId,doctorShedule));

    }

    @PostMapping("/shedule/add")
    ResponseEntity<String> addnewShift(@AuthenticationPrincipal Users users,
                                         @RequestBody DoctorShift doctorShift){

        String doctorId= users.getUserID();


        return ResponseEntity.ok(doctorSheduleService.addShift(doctorId,doctorShift));

    }

   @PutMapping("/shedule/update")

    ResponseEntity<String>  updateShift(@AuthenticationPrincipal Users users,
                                        @RequestBody DoctorShift doctorShift,
                                        @RequestParam(required = false)String shiftId){

       String doctorId= users.getUserID();

       System.out.println(doctorShift.getStartTime());
       System.out.println(doctorShift.getEndTime());

       return ResponseEntity.ok(doctorSheduleService.updateShift(doctorId,shiftId,doctorShift));

   }

   @DeleteMapping("/shedule/delete")

   ResponseEntity<String>  deleteShift(@AuthenticationPrincipal Users users,
                                       @RequestParam(required = false)String shiftId) {
       String doctorId= users.getUserID();


       return ResponseEntity.ok(doctorSheduleService.deleteShift(doctorId,shiftId));
    }

    @GetMapping("/shedule")
    ResponseEntity<List<DoctorAvailabilityResponse>>  getShift(@AuthenticationPrincipal Users users,
                                                               @RequestParam(required = false)String day) {
        String doctorId= users.getUserID();

        return ResponseEntity.ok(doctorSheduleService.showShift(doctorId,day));
    }
   }





