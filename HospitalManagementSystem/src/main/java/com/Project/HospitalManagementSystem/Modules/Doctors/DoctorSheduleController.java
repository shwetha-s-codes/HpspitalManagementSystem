package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.DTO.DoctorAvailabilityResponse;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShedule;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShift;
import com.Project.HospitalManagementSystem.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorSheduleController {

    @Autowired
    DoctorSheduleService doctorSheduleService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/shedule/create")
    ResponseEntity<String> createShedule(@RequestHeader("Authorization")String token,
                                   @RequestBody DoctorShedule doctorShedule){

        String Jtoken=token.substring(7);
        String doctorId= jwtService.extractUserId(Jtoken);


        return ResponseEntity.ok(doctorSheduleService.setDoctorShedule(doctorId,doctorShedule));

    }

    @PostMapping("/shedule/add")
    ResponseEntity<String> addnewShift(@RequestHeader("Authorization")String token,
                                         @RequestBody DoctorShift doctorShift){

        String Jtoken=token.substring(7);
        String doctorId= jwtService.extractUserId(Jtoken);


        return ResponseEntity.ok(doctorSheduleService.addShift(doctorId,doctorShift));

    }

   @PutMapping("/shedule/update")

    ResponseEntity<String>  updateShift(@RequestHeader("Authorization")String token,
                                        @RequestBody DoctorShift doctorShift,
                                        @RequestParam(required = false)String shiftId){

       String Jtoken=token.substring(7);
       String doctorId= jwtService.extractUserId(Jtoken);

       System.out.println(doctorShift.getStartTime());
       System.out.println(doctorShift.getEndTime());

       return ResponseEntity.ok(doctorSheduleService.updateShift(doctorId,shiftId,doctorShift));

   }

   @DeleteMapping("/shedule/delete")

   ResponseEntity<String>  deleteShift(@RequestHeader("Authorization")String token,
                                       @RequestParam(required = false)String shiftId) {
       String Jtoken=token.substring(7);
       String doctorId= jwtService.extractUserId(Jtoken);


       return ResponseEntity.ok(doctorSheduleService.deleteShift(doctorId,shiftId));
    }

    @GetMapping("/shedule")
    ResponseEntity<List<DoctorAvailabilityResponse>>  getShift(@RequestHeader("Authorization")String token,
                                                               @RequestParam(required = false)String day) {
        String Jtoken=token.substring(7);
        String doctorId= jwtService.extractUserId(Jtoken);


        return ResponseEntity.ok(doctorSheduleService.showShift(doctorId,day));
    }
   }





