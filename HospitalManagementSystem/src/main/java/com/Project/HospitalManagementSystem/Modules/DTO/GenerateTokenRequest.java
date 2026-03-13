package com.Project.HospitalManagementSystem.Modules.DTO;

import com.Project.HospitalManagementSystem.Modules.Admin.Admins;
import com.Project.HospitalManagementSystem.Modules.Admin.InvitationToken;
import org.springframework.beans.factory.annotation.Autowired;

public class GenerateTokenRequest {


    private Admins adminId;
    private InvitationToken roleId;
}
