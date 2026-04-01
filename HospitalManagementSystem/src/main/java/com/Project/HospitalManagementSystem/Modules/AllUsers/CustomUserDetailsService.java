package com.Project.HospitalManagementSystem.Modules.AllUsers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException{

        return usersRepo.findByemailId(emailId).orElseThrow(()-> new UsernameNotFoundException("User not found with emial:"+emailId));
    }

    public UserDetails loadUserById(String userId) throws UsernameNotFoundException{

        return usersRepo.findByemailId(userId).orElseThrow(()-> new UsernameNotFoundException("User not found with userId:"+userId));
    }
}
