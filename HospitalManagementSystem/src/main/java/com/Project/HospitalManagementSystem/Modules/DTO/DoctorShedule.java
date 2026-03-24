package com.Project.HospitalManagementSystem.Modules.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorShedule {

    private List<DoctorShift> doctorShifts;
}
