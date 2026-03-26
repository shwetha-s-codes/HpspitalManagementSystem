package com.Project.HospitalManagementSystem.Modules.AllUsers;

import com.Project.HospitalManagementSystem.Modules.Admin.InvitationToken;
import com.Project.HospitalManagementSystem.Modules.Admin.InvitationTokenRepo;
import com.Project.HospitalManagementSystem.Modules.Admin.TokenStatus;
import com.Project.HospitalManagementSystem.Modules.DTO.LoginRequest;
import com.Project.HospitalManagementSystem.Modules.DTO.RegisterRequest;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import com.Project.HospitalManagementSystem.Security.JwtUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Status;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    InvitationTokenRepo invitationTokenRepo;

    @Autowired
    RolesRepo rolesRepo;

    @Override
    @Transactional
    public String registerUser(@Valid RegisterRequest request, String token, Byte roleId) {
        Users newUser=new Users();
        String emailId = request.getEmailId().toLowerCase().trim();
        InvitationToken invitationToken;
        System.out.println(token);
        System.out.println(roleId);
        if(token!=null && roleId!=null){
            invitationToken= (invitationTokenRepo.findBytoken(token).orElseThrow(() -> new InvalidCredentialsException("Please use Correct token")));


            if(invitationToken.getRoleId()!=roleId){
                throw new InvalidCredentialsException("Unauthorized Registration");
            }
            if(invitationToken.getStatus()!=TokenStatus.ACTIVE){
                throw new InvalidCredentialsException("Token has already been used or Expired please request new token");
            }
            if(invitationToken.getExpiresAt().isBefore(LocalDateTime.now())){
                invitationToken.setStatus(TokenStatus.EXPIRED);
                invitationTokenRepo.save(invitationToken);
                throw new InvalidCredentialsException("Token has Expired please request new Token ");
            }
            if (usersRepo.existsByEmailId(emailId)) {
                throw new InvalidCredentialsException("Email Already Registered Login or Create new Account");
            }

            Roles currentRole = rolesRepo.findById(roleId)
                    .orElseThrow(() -> new InvalidCredentialsException("Role not found in the database"));

            newUser.setEmailId(emailId);
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.getRoles().add(currentRole);
            usersRepo.save(newUser);
            invitationToken.setStatus(TokenStatus.USED);
            invitationTokenRepo.save(invitationToken);


        }
        else
        {
            if (usersRepo.existsByEmailId(emailId)) {
                throw new InvalidCredentialsException("Email Already Registered Login or Create new Account");
            }
            Roles currentRole = rolesRepo.findByname("PATIENT")
                    .orElseThrow(() -> new InvalidCredentialsException("Role not found in the database"));


            newUser.setEmailId(emailId);
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.getRoles().add(currentRole);
            usersRepo.save(newUser);
        }
        return "Registration Successfull";
    }


    @Transactional
    public  String LoginUser(LoginRequest request){

        String emailId=request.getEmailId().toLowerCase().trim();
        String password=request.getPassword();
        Users user = usersRepo.findByemailId(emailId)
                    .orElseThrow(() -> new InvalidCredentialsException("User not found"));

            if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
                throw new InvalidCredentialsException("Invalid password");
            }

            if(!user.isActive()){
                throw new RuntimeException("Account is inactive");
            }
        // Get the first role from the set
        Roles role = user.getRoles()
                .stream()
                .findFirst()
                .orElseThrow(() -> new InvalidCredentialsException("No role assigned"));

        Byte roleId = role.getRoleID();
        String roleName = role.getName(); // e.g. "ADMIN", "DOCTOR"

// Now generate token with roleId
        return jwtUtil.generateToken(user.getEmailId(), roleId, roleName,user.getUserID());




        }


}

