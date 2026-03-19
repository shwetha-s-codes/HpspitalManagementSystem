package com.Project.HospitalManagementSystem.Modules.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

// Nurse - no extra fields but keeps it clean and extensible
@Data
@EqualsAndHashCode(callSuper = true)
public class NurseProfileRequest extends BaseProfileRequest {
}
