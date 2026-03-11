package com.Project.HospitalManagementSystem.Controller;

import com.Project.HospitalManagementSystem.DTO.CreateUser;
import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {



    @Autowired
    UsersService usersService;

    @PostMapping("/createUser")

    public ResponseEntity<Users> createUser(@RequestBody CreateUser user){

        return usersService.createNewUser(user);


        
    }


}
