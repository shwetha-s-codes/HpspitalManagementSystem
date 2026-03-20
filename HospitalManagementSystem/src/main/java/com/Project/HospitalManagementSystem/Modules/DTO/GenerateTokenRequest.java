package com.Project.HospitalManagementSystem.Modules.DTO;

import com.Project.HospitalManagementSystem.Modules.Admin.Admins;
import com.Project.HospitalManagementSystem.Modules.Admin.InvitationToken;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateTokenRequest {

    @NotBlank(message = "please provide the recipient mail")
    @Email
    private String email;

    @NotBlank(message="Please provide the role to which yoo need the token for")
    private  String roleName;



}
