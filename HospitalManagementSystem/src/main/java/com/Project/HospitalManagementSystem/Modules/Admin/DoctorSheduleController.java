package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShedule;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShift;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@Slf4j
public class DoctorSheduleController {

    @Autowired
    DoctorSheduleService doctorSheduleService;



    @PostMapping("/shedule/create")
    @PreAuthorize("hasAuthority('Admin')")
    ResponseEntity<String> createShedule(@RequestParam("doctorId")String doctorId,
                                   @RequestBody DoctorShedule doctorShedule){
        log.info(doctorId);





        return ResponseEntity.ok(doctorSheduleService.setDoctorShedule(doctorId,doctorShedule));

    }

    @PostMapping("/shedule/add")
    @PreAuthorize("hasAuthority('Admin')")
    ResponseEntity<String> addnewShift(@RequestParam("doctorId")String doctorId,
                                         @RequestBody DoctorShift doctorShift){




        return ResponseEntity.ok(doctorSheduleService.addShift(doctorId,doctorShift));

    }

   @PutMapping("/shedule/update")
   @PreAuthorize("hasAuthority('Admin')")

    ResponseEntity<String>  updateShift(@RequestParam("doctorId")String doctorId,
                                        @RequestBody DoctorShift doctorShift,
                                        @RequestParam(required = false)String shiftId){



       System.out.println(doctorShift.getStartTime());
       System.out.println(doctorShift.getEndTime());

       return ResponseEntity.ok(doctorSheduleService.updateShift(doctorId,shiftId,doctorShift));

   }

    @DeleteMapping("/shifts/{availabilityId}/doctor/{doctorId}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<String> deleteShift(
            @PathVariable String availabilityId,
            @PathVariable String doctorId) {
        doctorSheduleService.deleteShift(availabilityId, doctorId);
        return ResponseEntity.ok("Shift Deleted Successfully");
    }

    @PutMapping("/shifts/{availabilityId}/doctor/{doctorId}/restore")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<String> restoreShift(
            @PathVariable String availabilityId,
            @PathVariable String doctorId) {
        doctorSheduleService.restoreShift(availabilityId, doctorId);
        return ResponseEntity.ok("Shift Restored Successfully");
    }
    }








