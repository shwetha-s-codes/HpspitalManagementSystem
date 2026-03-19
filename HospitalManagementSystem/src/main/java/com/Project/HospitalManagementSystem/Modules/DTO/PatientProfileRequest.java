package com.Project.HospitalManagementSystem.Modules.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

// Patient
@Data
@EqualsAndHashCode(callSuper = true)
public class PatientProfileRequest extends BaseProfileRequest {
    private Byte age;
    private String city;
}
