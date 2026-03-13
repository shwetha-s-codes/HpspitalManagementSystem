package com.Project.HospitalManagementSystem.Modules.AllUsers;

import com.Project.HospitalManagementSystem.Modules.DTO.LoginRequest;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import com.Project.HospitalManagementSystem.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


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

            /*String role = user.getRoles().iterator().next().getname();*/

            return jwtUtil.generateToken(user.getEmailId());
        }


    }

