package com.Project.HospitalManagementSystem.Modules.Admin;


import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/token")

    public String tokenGeneration(@Valid @RequestBody GenerateTokenRequest request){
        return adminService.tokenGeneration(request);
    }
    @DeleteMapping("/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        return adminService.deleteUser(email);
    }

}
