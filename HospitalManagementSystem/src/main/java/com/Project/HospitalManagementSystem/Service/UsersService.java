package com.Project.HospitalManagementSystem.Service;

import com.Project.HospitalManagementSystem.DTO.CreateUser;
import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Roles;
import com.Project.HospitalManagementSystem.Repository.RoleRepo;
import com.Project.HospitalManagementSystem.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepo roleRepo;




    //used to store the hash value of password instead of actual password
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public CreateUser createNewUser(CreateUser request)
    {
        if(usersRepository.existsByEmailID(request.getEmailID())){
            throw new RuntimeException("Email already registered:"+request.getEmailID());

        }
        Set<Roles> roles=new HashSet<>();
        for(String roleName: request.getRoleNames()){
            Roles role = roleRepo.findByRoleName(roleName).orElseThrow(()-> new RuntimeException("Role not found:"+roleName));


        }
        Users newuser = new Users();
        newuser.setPassword(request.getPassword());
        newuser.setEmailID(request.getEmailID());
        newuser.setRoles(role);

        return UsersRepository.save(newuser);
    }


}
