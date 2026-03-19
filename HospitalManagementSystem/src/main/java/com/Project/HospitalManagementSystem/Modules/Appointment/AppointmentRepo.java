package com.Project.HospitalManagementSystem.Modules.Appointment;

import com.Project.HospitalManagementSystem.Modules.Doctors.Doctor;
import com.Project.HospitalManagementSystem.Modules.Patients.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,String > {

    void deleteByDoctor(Doctor doctor);

    void deleteByPatient(Patient patient);
}
