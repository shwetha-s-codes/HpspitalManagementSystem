package com.Project.HospitalManagementSystem.Modules.Profile.Service;

import com.Project.HospitalManagementSystem.Modules.AllUsers.GenderRepo;
import com.Project.HospitalManagementSystem.Modules.AllUsers.Users;
import com.Project.HospitalManagementSystem.Modules.AllUsers.UsersRepo;
import com.Project.HospitalManagementSystem.Modules.DTO.*;
import com.Project.HospitalManagementSystem.Modules.Doctors.Doctor;
import com.Project.HospitalManagementSystem.Modules.Doctors.DoctorRepo;
import com.Project.HospitalManagementSystem.Modules.Exceptions.InvalidCredentialsException;
import com.Project.HospitalManagementSystem.Modules.LookUpTables.Gender;
import com.Project.HospitalManagementSystem.Modules.Nurses.Nurse;
import com.Project.HospitalManagementSystem.Modules.Nurses.NurseRepo;
import com.Project.HospitalManagementSystem.Modules.Patients.Patient;
import com.Project.HospitalManagementSystem.Modules.Patients.PatientRepo;
import com.Project.HospitalManagementSystem.Modules.Staff.Staff;
import com.Project.HospitalManagementSystem.Modules.Staff.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileServiceImpl implements ProfileService{



        @Autowired
        private UsersRepo userRepo;
        @Autowired private DoctorRepo doctorRepo;
        @Autowired private NurseRepo nurseRepo;
        @Autowired private PatientRepo patientRepo;
        @Autowired private StaffRepo staffRepo;
        @Autowired private GenderRepo genderRepo;

        // ─── CREATE ──────────────────────────────────────────────

        @Transactional
        public String createDoctorProfile(String userId, DoctorProfileRequest request) {
            Users user = getUser(userId);

            if (doctorRepo.existsByUser(user)) {
                throw new InvalidCredentialsException("Profile already exists, use update endpoint");
            }

            validateBaseFields(request);
            if (request.getSpecialization() == null || request.getSpecialization().isBlank()) {
                throw new InvalidCredentialsException("Specialization is required");
            }

            Gender gender = getGender(request.getGenderId());

            Doctor doctor = new Doctor();
            doctor.setUser(user);
            doctor.setFirstName(request.getFirstName());
            doctor.setLastName(request.getLastName());
            doctor.setPhoneNumber(request.getPhoneNumber());
            doctor.setSpecialization(request.getSpecialization());
            doctor.setGender(gender);
            doctorRepo.save(doctor);

            return "Doctor profile created successfully";
        }

        @Transactional
        public String createNurseProfile(String userId, NurseProfileRequest request) {
            Users user = getUser(userId);

            if (nurseRepo.existsByUser(user)) {
                throw new InvalidCredentialsException("Profile already exists, use update endpoint");
            }

            validateBaseFields(request);
            Gender gender = getGender(request.getGenderId());

            Nurse nurse = new Nurse();
            nurse.setUser(user);
            nurse.setFirstName(request.getFirstName());
            nurse.setLastName(request.getLastName());
            nurse.setPhoneNumber(request.getPhoneNumber());
            nurse.setGender(gender);
            nurseRepo.save(nurse);

            return "Nurse profile created successfully";
        }

        @Transactional
        public String createPatientProfile(String userId, PatientProfileRequest request) {
            Users user = getUser(userId);

            if (patientRepo.existsByUser(user)) {
                throw new InvalidCredentialsException("Profile already exists, use update endpoint");
            }

            validateBaseFields(request);
            if (request.getAge() == null) {
                throw new InvalidCredentialsException("Age is required");
            }
            if (request.getCity() == null || request.getCity().isBlank()) {
                throw new InvalidCredentialsException("City is required");
            }

            Gender gender = getGender(request.getGenderId());

            Patient patient = new Patient();
            patient.setUser(user);
            patient.setFirstName(request.getFirstName());
            patient.setLastName(request.getLastName());
            patient.setPhoneNumber(request.getPhoneNumber());
            patient.setAge(request.getAge());
            patient.setCity(request.getCity());
            patient.setGender(gender);
            patientRepo.save(patient);

            return "Patient profile created successfully";
        }

        @Transactional
        public String createStaffProfile(String UserId, StaffProfileRequest request) {
            Users user = getUser(UserId);

            if (staffRepo.existsByUser(user)) {
                throw new InvalidCredentialsException("Profile already exists, use update endpoint");
            }

            validateBaseFields(request);
            Gender gender = getGender(request.getGenderId());

            Staff staff = new Staff();
            staff.setUser(user);
            staff.setFirstName(request.getFirstName());
            staff.setLastName(request.getLastName());
            staff.setPhoneNumber(request.getPhoneNumber());
            staff.setGender(gender);
            staffRepo.save(staff);

            return "Staff profile created successfully";
        }



        @Transactional
        public String updateDoctorProfile(String userId, DoctorProfileRequest request) {
            Users user = getUser(userId);

            Doctor doctor = doctorRepo.findByUser(user)
                    .orElseThrow(() -> new InvalidCredentialsException("Profile not found, create profile first"));

            // Retain old values if null
            doctor.setFirstName(request.getFirstName() != null ? request.getFirstName() : doctor.getFirstName());
            doctor.setLastName(request.getLastName() != null ? request.getLastName() : doctor.getLastName());
            doctor.setPhoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : doctor.getPhoneNumber());
            doctor.setSpecialization(request.getSpecialization() != null ? request.getSpecialization() : doctor.getSpecialization());

            if (request.getGenderId() != null) {
                doctor.setGender(getGender(request.getGenderId()));
            }

            doctorRepo.save(doctor);
            return "Doctor profile updated successfully";
        }

        @Transactional
        public String updateNurseProfile(String userId, NurseProfileRequest request) {
            Users user = getUser(userId);

            Nurse nurse = nurseRepo.findByUser(user)
                    .orElseThrow(() -> new InvalidCredentialsException("Profile not found, create profile first"));

            nurse.setFirstName(request.getFirstName() != null ? request.getFirstName() : nurse.getFirstName());
            nurse.setLastName(request.getLastName() != null ? request.getLastName() : nurse.getLastName());
            nurse.setPhoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : nurse.getPhoneNumber());

            if (request.getGenderId() != null) {
                nurse.setGender(getGender(request.getGenderId()));
            }

            nurseRepo.save(nurse);
            return "Nurse profile updated successfully";
        }

        @Transactional
        public String updatePatientProfile(String userId, PatientProfileRequest request) {
            Users user = getUser(userId);

            Patient patient = patientRepo.findByUser(user)
                    .orElseThrow(() -> new InvalidCredentialsException("Profile not found, create profile first"));

            patient.setFirstName(request.getFirstName() != null ? request.getFirstName() : patient.getFirstName());
            patient.setLastName(request.getLastName() != null ? request.getLastName() : patient.getLastName());
            patient.setPhoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : patient.getPhoneNumber());
            patient.setAge(request.getAge() != null ? request.getAge() : patient.getAge());
            patient.setCity(request.getCity() != null ? request.getCity() : patient.getCity());

            if (request.getGenderId() != null) {
                patient.setGender(getGender(request.getGenderId()));
            }

            patientRepo.save(patient);
            return "Patient profile updated successfully";
        }

        @Transactional
        public String updateStaffProfile(String userId, StaffProfileRequest request) {
            Users user = getUser(userId);

            Staff staff = staffRepo.findByUser(user)
                    .orElseThrow(() -> new InvalidCredentialsException("Profile not found, create profile first"));

            staff.setFirstName(request.getFirstName() != null ? request.getFirstName() : staff.getFirstName());
            staff.setLastName(request.getLastName() != null ? request.getLastName() : staff.getLastName());
            staff.setPhoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : staff.getPhoneNumber());

            if (request.getGenderId() != null) {
                staff.setGender(getGender(request.getGenderId()));
            }

            staffRepo.save(staff);
            return "Staff profile updated successfully";
        }



        private Users getUser(String userId) {
            return userRepo.findById(userId)
                    .orElseThrow(() -> new InvalidCredentialsException("User not found"));
        }

        private Gender getGender(Byte genderId) {
            if (genderId == null) throw new InvalidCredentialsException("Gender is required");
            return genderRepo.findById(genderId)
                    .orElseThrow(() -> new InvalidCredentialsException("Invalid gender"));
        }

        // Validates fields common to all roles
        private void validateBaseFields(BaseProfileRequest request) {
            if (request.getFirstName() == null || request.getFirstName().isBlank())
                throw new InvalidCredentialsException("First name is required");
            if (request.getLastName() == null || request.getLastName().isBlank())
                throw new InvalidCredentialsException("Last name is required");
            if (request.getPhoneNumber() == null || request.getPhoneNumber().isBlank())
                throw new InvalidCredentialsException("Phone number is required");
            if (request.getGenderId() == null)
                throw new InvalidCredentialsException("Gender is required");
        }
    }

