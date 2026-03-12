package com.Project.HospitalManagementSystem.Modules.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;

//DTO handle the User Registrations
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message="Email is required")
    @Email(message="Invalid email format")
    private String emailId;

    @NotBlank (message="Password is required")
    @Size(min=8,message="Password must be atleast 8 characters")
    private  String password;
}
