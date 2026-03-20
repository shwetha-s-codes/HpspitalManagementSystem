package com.Project.HospitalManagementSystem.Modules.Admin;


import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;
import com.Project.HospitalManagementSystem.Security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/admin/token")

    public void tokenGeneration(@Valid @RequestBody GenerateTokenRequest request,@RequestHeader("authorization")String authHeader){
        String  jToken=authHeader.substring(7);
        System.out.println(jToken);
        String adminmail= jwtUtil.extractEmail(jToken);
        System.out.println(adminmail);
         adminService.tokenGeneration(request,adminmail);
    }
    @DeleteMapping("/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        return adminService.deleteUser(email);
    }

}
