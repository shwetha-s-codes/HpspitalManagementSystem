package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.DTO.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {

    /*@Autowired
    private RegisterRequest request;*/

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/register")

    public ResponseEntity<?> registerDoctor(@Valid @RequestBody RegisterRequest request){

        String message= doctorService.registerDoctor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);


    }

}
