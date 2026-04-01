package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorAvailabilityResponse;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShedule;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorShift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorSheduleService {
    public String setDoctorShedule(String userId,DoctorShedule doctorShedule);
    public String addShift(String userId,DoctorShift shift);
    public String updateShift(String userId,String shiftId, DoctorShift shift);
    public String deleteShift(String userId, String shiftId);
    public Page<DoctorAvailabilityResponse> showShift(Users users, String day, String doctorId,Pageable pageable);
}
