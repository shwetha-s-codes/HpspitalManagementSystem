package com.Project.HospitalManagementSystem.Modules.DTO;

import com.Project.HospitalManagementSystem.Modules.Admin.Admins;
import com.Project.HospitalManagementSystem.Modules.Admin.InvitationToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateTokenRequest {


    private String  adminId;
    private  String roleName;


}
