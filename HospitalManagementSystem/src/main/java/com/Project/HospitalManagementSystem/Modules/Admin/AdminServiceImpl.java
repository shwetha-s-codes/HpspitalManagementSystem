package com.Project.HospitalManagementSystem.Modules.Admin;

import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.AllUsers.UsersRepo;
import com.Project.HospitalManagementSystem.Modules.Appointment.AppointmentRepo;
import com.Project.HospitalManagementSystem.Modules.DTO.DoctorSearchResponse;
import com.Project.HospitalManagementSystem.Modules.DTO.GenerateTokenRequest;
import com.Project.HospitalManagementSystem.Modules.Doctors.DoctorRepo;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import com.Project.HospitalManagementSystem.Modules.Nurses.NurseRepo;
import com.Project.HospitalManagementSystem.Modules.Patients.PatientRepo;
import com.Project.HospitalManagementSystem.Modules.Staff.StaffRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService{


    @Autowired
    private AdminRepo  adminRepo;

    @Autowired
    private UserInvitation userInvitation;

    @Autowired
    private UsersRepo userRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private NurseRepo nurseRepo;

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    public void tokenGeneration(GenerateTokenRequest request, String adminId){

        log.info(adminId);

        Admins admins=adminRepo.findById(adminId).orElseThrow(()-> new InvalidCredentialsException("This is not a admin mail you can't access this resource"));
        System.out.println(admins);
        if(admins==null)
        {
            throw new InvalidCredentialsException("Admin Database not loaded");
        }

        System.out.println(admins.getAdminId());


        userInvitation.generateToken(request,admins.getAdminId());
    }

    @Transactional
    public String deleteUser(String email) {

        Users user = userRepo.findByemailId(email)
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        String roleName = user.getRoles()
                .stream()
                .findFirst()
                .orElseThrow(() -> new InvalidCredentialsException("Role not found"))
                .getName();

        switch (roleName) {

            case "DOCTOR" -> {
                doctorRepo.findByUser(user).ifPresent(doctor -> {
                    // Delete appointments where this doctor is referenced first
                    appointmentRepo.deleteByDoctor(doctor);
                    doctorRepo.delete(doctor);
                });
            }

            case "PATIENT" -> {
                patientRepo.findByUser(user).ifPresent(patient -> {
                    // Delete appointments where this patient is referenced first
                    appointmentRepo.deleteByPatient(patient);
                    patientRepo.delete(patient);
                });
            }

            case "NURSE"  -> nurseRepo.findByUser(user).ifPresent(nurseRepo::delete);
            case "STAFF"  -> staffRepo.findByUser(user).ifPresent(staffRepo::delete);

            default -> throw new InvalidCredentialsException("Unknown role");
        }

        userRepo.delete(user);
        return email + " deleted successfully";
    }
    @Transactional
    public List<DoctorSearchResponse>  searchDoctor(String name){
        if (name==null)
            throw new InvalidCredentialsException("Kindly Write a  name");
        else
            return doctorRepo.searchName(name);
    }
}
