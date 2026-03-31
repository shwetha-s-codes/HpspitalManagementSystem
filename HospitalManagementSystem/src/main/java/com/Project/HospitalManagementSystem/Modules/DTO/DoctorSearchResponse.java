package com.Project.HospitalManagementSystem.Modules.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSearchResponse {

    String doctorId;
    String firstname;
    String lastname;
    String specialization;

}
