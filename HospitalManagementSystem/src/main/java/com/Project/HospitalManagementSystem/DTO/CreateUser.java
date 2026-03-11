package com.Project.HospitalManagementSystem.DTO;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import lombok.Data;

import java.util.Set;

@Data
public class CreateUser {
    private String emailID;
    private String password;
    private Set<String> roleNames;



}
