package com.Project.HospitalManagementSystem.Modules.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
public class BaseProfileRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Byte genderId;
}

