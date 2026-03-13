package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.AllUsers.RolesRepo;
import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

public class UserInvitationServiceImpl implements UserInvitation{

    @Autowired
    private InvitationTokenRepo invitationTokenRepo;

    private GenerateTokenRequest request;

    @Autowired
    private RolesRepo rolesRepo;

    private InvitationToken invitationToken;
    private  Roles role;

    @Transactional
    public String  generateToken(GenerateTokenRequest request){
        String token=generateRandomToken();
        Roles role=rolesRepo.findByname(request.getroleName());
        invitationToken.setToken(token);
        invitationToken.setRoleId(role.getRoleID());




        return "localhost://http/8080/auth/register";

    }

    public String generateRandomToken(){
        SecureRandom random=new SecureRandom();
        byte[] bytes=new byte[64];
        random.nextBytes(bytes);
        return  Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
    }

}
