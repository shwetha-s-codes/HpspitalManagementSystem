package com.Project.HospitalManagementSystem.Modules.AllUsers;

import com.Project.HospitalManagementSystem.Modules.DTO.LoginRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")

    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request,
                                                @RequestParam(required = false) String token,
                                                @RequestParam(required = false) Byte roleId)
    {

        String message= usersService.registerUser(request,token,roleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);


    }




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){

        String token = usersService.LoginUser(request);

        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "token", token
        ));
    }
}
