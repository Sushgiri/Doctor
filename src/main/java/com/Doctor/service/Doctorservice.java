package com.Doctor.service;

import com.Doctor.config.ResttemplateConfig;
import com.Doctor.entity.Doctor;
import com.Doctor.exception.DataAlreadyExists;
import com.Doctor.exception.ResourceNotFoundExcecption;
import com.Doctor.payload.Logindto;
import com.Doctor.payload.homeview;
import com.Doctor.repository.DoctorRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@EnableMongoRepositories("com.Doctor.repository")
@AllArgsConstructor

public class Doctorservice {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ResttemplateConfig resttemplate;
    private final ModelMapper modelMapper;


    public String createdoctoraccount(Doctor doctor) {
        //  Doctor doctor = MaptoDoctor(doctordto);
        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new DataAlreadyExists("Email Already Registered with an existing acccount");
        }
        if (doctorRepository.existsByPhone(doctor.getPhone())) {
            throw new DataAlreadyExists("Phone Already Registered with an existing acccount");
        }
        try {
            String createddoctorid = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            String todaydate = date.toString();
            doctor.setSignupdate(todaydate.toString());
            doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
            doctor.setDoctorId(createddoctorid);
            doctor.setAvailableSlots(doctor.getAvailableSlots());
            doctorRepository.save(doctor);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong ,Account Creation Failed");

        }
        return "DoctorAccount Created Successfully";
    }

    public String deletedoctoraccount(String DoctorId,Logindto dto) {
        Doctor doctor1 = doctorRepository.findByEmail(dto.getEmail());
        boolean isPasswordMatch = passwordEncoder.matches(dto.getPassword(), doctor1.getPassword());
        if (isPasswordMatch) {
            doctorRepository.deleteById(doctor1.getId());
            return "Account is deleted";
        } else {
            throw new ResourceNotFoundExcecption("Invalid password Entered,Enter valid password");
        }
    }

    public homeview logindoctoraccount(Logindto logindto) {
        Doctor doctor1 = doctorRepository.findByEmail(logindto.getEmail());
        if(doctor1 == null){
            throw new ResourceNotFoundExcecption("There is No account with email"+logindto.getEmail());
        }
        if (doctor1 != null && logindto.getEmail().equals(doctor1.getEmail()) && passwordEncoder.matches(logindto.getPassword(), doctor1.getPassword())) {
            homeview homeview = new homeview();
            homeview returnhome = new homeview();
            returnhome.setDoctorId("UNIQUE ID                :     "+doctor1.getDoctorId());
            returnhome.setName("NAME                         :     "+doctor1.getDoctorName());
            returnhome.setCreateSlot("CREATE AVALIABLE SLOTS :     "+homeview.getCreateSlot()+doctor1.getDoctorId()+"/yyyy-mm-dd");
            returnhome.setDeleteacount("DELETE ACCOUNT       :     "+homeview.getDeleteacount()+doctor1.getDoctorId());
            returnhome.setViewAvailableSlots("AVIALABLE SLOTS:     "+homeview.getViewAvailableSlots()+doctor1.getDoctorId());
            returnhome.setDeleteSlot("DELETE SLOTS           :     "+homeview.getDeleteSlot()+doctor1.getDoctorId()+"/yyyy-mm-dd");
            returnhome.setUpateProfile("UPDATE PROFILE       :     "+homeview.getUpateProfile()+doctor1.getDoctorId());
            returnhome.setUpdateSLot("UPDATE SOLT            :     "+homeview.getUpdateSLot()+doctor1.getDoctorId()+"/yyyy-mm-dd");
            returnhome.setDeleteacount("DELETE ACCOUNT       :     "+homeview.getDeleteacount()+doctor1.getDoctorId());
            returnhome.setProfileDetails("PROFILE DETAILS    :     "+homeview.getProfileDetails()+doctor1.getDoctorId());
            return returnhome;
        } else {
            throw new ResourceNotFoundExcecption("Invalid Credentials. Check entered email and password again");
        }
    }
    public List<Doctor> getalldoctors() {
        List<Doctor> all = doctorRepository.findAll();
        return all;
    }

    public String updatedoctoraccoutn(String DoctorId,Doctor doctor) {
        Doctor doctor1 = doctorRepository.findByDoctorId(DoctorId);

        if(doctor1 ==null){
            throw  new ResourceNotFoundExcecption("There is no Doctor account with DoctorId"+DoctorId);
        }
        doctor1.setDoctorId(DoctorId);
        Doctor updated = modelMapper.map(doctor, doctor1.getClass());
        doctorRepository.save(updated);
        return "Account Credentials updated Successfully";
    }

    public Doctor gatprofiledetails(String doctorId) {
        Doctor byDoctorId = doctorRepository.findByDoctorId(doctorId);
        return  byDoctorId;
    }



}
