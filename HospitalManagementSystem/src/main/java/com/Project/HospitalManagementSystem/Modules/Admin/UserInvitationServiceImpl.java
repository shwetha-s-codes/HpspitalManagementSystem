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


    private InvitationToken invitationToken;


    private  Roles role;

    @Transactional
    public String  generateToken(GenerateTokenRequest request){
        String token=generateRandomToken();

        Roles currentRole= (rolesRepo.findByname(request.getRoleName()).orElseThrow(() -> new InvalidCredentialsException("Role not found")));
        Byte roleId=currentRole.getRoleID();

        Admins adminId= adminRepo.getReferenceById(request.getAdminId());

        InvitationToken newtoken=new InvitationToken();
        newtoken.setToken(token);
        newtoken.setRoleId(roleId);
        newtoken.setAdminId(adminId);
        invitationTokenRepo.save(newtoken);




        return "localhost://http/8080/auth/register?token="+token+"&&roleId="+roleId;

    }

    public String generateRandomToken(){
        SecureRandom random=new SecureRandom();
        byte[] bytes=new byte[64];
        random.nextBytes(bytes);
        return  Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }


}
