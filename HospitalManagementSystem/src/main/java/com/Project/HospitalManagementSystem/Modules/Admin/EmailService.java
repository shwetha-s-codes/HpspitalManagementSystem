package com.Project.HospitalManagementSystem.Modules.Admin;

public interface EmailService {

    public void sendEmail(Byte roleId,String token,String email);
}
