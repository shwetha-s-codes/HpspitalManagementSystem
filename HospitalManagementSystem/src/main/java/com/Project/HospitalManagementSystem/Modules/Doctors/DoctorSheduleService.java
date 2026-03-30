package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.DTO.DoctorAvailabilityResponse;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShedule;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShift;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface DoctorSheduleService {
    public String setDoctorShedule(String userId,DoctorShedule doctorShedule);
    public String addShift(String userId,DoctorShift shift);
    public String updateShift(String userId,String shiftId, DoctorShift shift);
    public String deleteShift(String userId, String shiftId);
    public List<DoctorAvailabilityResponse>  showShift(String userId, String day);
}
