package com.Project.HospitalManagementSystem.Security;

import com.Project.HospitalManagementSystem.Security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Autowired
        private JwtAuthenticationEntryPoint authenticationEntryPoint; // ← inject it

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .exceptionHandling(ex -> ex
                            .authenticationEntryPoint(authenticationEntryPoint) // ← add this
                    )
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
                            .requestMatchers("/api/auth/admin/**").hasRole("ADMIN")
                            .anyRequest().authenticated()
                    );

            http.addFilterBefore(jwtAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }
    }
