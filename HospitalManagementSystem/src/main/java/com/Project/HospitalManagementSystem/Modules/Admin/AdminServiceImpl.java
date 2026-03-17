package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{


    @Autowired
    private AdminRepo  adminRepo;

    @Autowired
    private UserInvitation userInvitation;

    public String tokenGeneration(GenerateTokenRequest request){

        if(!adminRepo.existsById(request.getAdminId())){
            throw new InvalidCredentialsException("Invalid Admin Id");

        }
        return userInvitation.generateToken(request);
    }
}
