package com.Project.HospitalManagementSystem.Modules.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

// Doctor
@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorProfileRequest extends BaseProfileRequest {
    private String specialization;
}
