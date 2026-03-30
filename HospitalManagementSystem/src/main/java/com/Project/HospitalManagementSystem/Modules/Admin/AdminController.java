package com.Project.HospitalManagementSystem.Modules.Admin;


import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;
import com.Project.HospitalManagementSystem.Security.JwtService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/admin/token")

    public void tokenGeneration(@Valid @RequestBody GenerateTokenRequest request,@RequestHeader("authorization")String authHeader){
        String  jToken=authHeader.substring(7);
        System.out.println(jToken);
        String adminId= jwtService.extractUserId(jToken);
        log.info(adminId);
         adminService.tokenGeneration(request,adminId);
    }
    @DeleteMapping("/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        return adminService.deleteUser(email);
    }

}
