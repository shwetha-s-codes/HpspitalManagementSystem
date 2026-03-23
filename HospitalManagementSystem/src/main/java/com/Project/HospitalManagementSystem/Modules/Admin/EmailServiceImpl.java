package com.Project.HospitalManagementSystem.Modules.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;



    public void sendEmail(Byte roleId,String token,String email){
        String registrationLink="http://localhost:8080/api/auth/register?token="+token+"roleId="+roleId;
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("shwethalearns247@gmail.com");
        message.setTo(email);
        message.setSubject("Your Link for Registering in HMS");
        message.setText(registrationLink);
        mailSender.send(message);

    }

}
