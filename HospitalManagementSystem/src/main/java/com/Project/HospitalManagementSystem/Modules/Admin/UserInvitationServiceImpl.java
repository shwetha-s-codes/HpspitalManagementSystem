package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.AllUsers.RolesRepo;
import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserInvitationServiceImpl implements UserInvitation{

    @Autowired
    private InvitationTokenRepo invitationTokenRepo;

    private GenerateTokenRequest request;

    @Autowired
    private RolesRepo rolesRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private EmailService emailService;



    @Transactional
    public void  generateToken(GenerateTokenRequest request,String adminId){
        String token=generateRandomToken();

        Roles currentRole= (rolesRepo.findByname(request.getRoleName()).orElseThrow(() -> new InvalidCredentialsException("Role not found")));
        Byte roleId=currentRole.getRoleID();
        InvitationToken newtoken=new InvitationToken();
        newtoken.setToken(token);
        newtoken.setRoleId(roleId);
        newtoken.setAdminId(adminRepo.getReferenceById(adminId));
        invitationTokenRepo.save(newtoken);

        emailService.sendEmail(roleId,token, request.getEmail());





    }

    public String generateRandomToken(){
        SecureRandom random=new SecureRandom();
        byte[] bytes=new byte[64];
        random.nextBytes(bytes);
        return  Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }


}
