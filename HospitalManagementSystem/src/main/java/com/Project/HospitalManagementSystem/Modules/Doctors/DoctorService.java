package com.Project.HospitalManagementSystem.Modules.Doctors;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;

public interface DoctorService {
    public void requestShiftRestore(String availabilityId, String adminId, Users doctor);
}
