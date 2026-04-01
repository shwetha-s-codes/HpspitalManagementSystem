package com.Project.HospitalManagementSystem.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter extends OncePerRequestFilter{


    private final UserDetailsService userDetailsService;
    private final ReferenceTokenService referenceTokenService;

    @Override
    protected  void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException{
        String authHeader=request.getHeader("Authorization");
        if(authHeader==null||!authHeader.startsWith("Bearer ")){
            log.error("Authentication Header not working");
            filterChain.doFilter(request,response);
            return;
        }

        String token=authHeader.substring(7);

        try {

            ReferenceToken referenceToken=referenceTokenService.validateReferenceToken(token);
            log.info(token);
            log.info(referenceToken.getUser().getEmailId());
            String emailId=referenceToken.getUser().getEmailId();
            log.info(emailId);
            UserDetails userDetails = userDetailsService.loadUserByUsername(emailId);
            log.info(userDetails.getAuthorities().toString());

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        catch (RuntimeException ex){
            log.error("Authentication Failed");
            SecurityContextHolder.clearContext();
        }
            filterChain.doFilter(request, response);

    }


}
