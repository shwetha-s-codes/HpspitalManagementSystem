package com.Project.HospitalManagementSystem.Modules.AllUsers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String eamilId) throws UsernameNotFoundException{

        return usersRepo.findByemailId(eamilId).orElseThrow(()-> new UsernameNotFoundException("User not found with emial:"+eamilId));
    }
}
