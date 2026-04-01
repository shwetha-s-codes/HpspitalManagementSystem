package com.Project.HospitalManagementSystem.Modules.Admin;


import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorSearchResponse;
import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;
import com.Project.HospitalManagementSystem.Security.JwtService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/token")

    public void tokenGeneration(@Valid @RequestBody GenerateTokenRequest request, @AuthenticationPrincipal Users users){
        /*String  jToken=authHeader.substring(7);
        log.info(jToken);
        String adminId= jwtService.extractUserId(jToken);
        log.info(adminId);*/

        //Replacing JWT tokens with referenced tokens
        String adminId=users.getUserID();
        adminService.tokenGeneration(request,adminId);
    }
    @DeleteMapping("/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        return adminService.deleteUser(email);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DoctorSearchResponse>> searchUser(@RequestParam("name") String name){
        log.info(name);
        return ResponseEntity.ok(adminService.searchDoctor(name
        ));

    }


}
