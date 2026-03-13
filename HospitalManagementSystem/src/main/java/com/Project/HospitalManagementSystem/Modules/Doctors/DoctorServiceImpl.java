package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.AllUsers.RolesRepo;
import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.AllUsers.UsersRepo;
import com.Project.HospitalManagementSystem.Modules.DTO.RegisterRequest;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepo rolesRepo;

    private RegisterRequest registerRequest;


    public String registerDoctor(@Valid RegisterRequest request) {
        String emailId = request.getEmailId().toLowerCase().trim();

        if (usersRepo.existsByEmailId(emailId)) {
            throw new InvalidCredentialsException("Email Already Registered");
        }
        Roles doctorRole = rolesRepo.findByname("DOCTOR")
                .orElseThrow(() -> new InvalidCredentialsException("Role not found in the database"));


        Users newUser = new Users();
        newUser.setEmailId(emailId);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.getRoles().add(doctorRole);
        usersRepo.save(newUser);
        return "Doctor Registered Successfully";

    }
}







