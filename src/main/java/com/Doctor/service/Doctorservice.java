package com.Doctor.service;

import com.Doctor.config.ResttemplateConfig;
import com.Doctor.entity.Doctor;
import com.Doctor.entity.Reviews;
import com.Doctor.exception.DataAlreadyExists;
import com.Doctor.exception.ResourceNotFoundExcecption;
import com.Doctor.payload.Doctordto;
import com.Doctor.payload.Logindto;
import com.Doctor.payload.homeview;
import com.Doctor.repository.DoctorRepository;


import com.Doctor.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;



@Service
@EnableMongoRepositories("com.Doctor.repository")
@AllArgsConstructor

public class Doctorservice {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private ResttemplateConfig resttemplate;
    private final ModelMapper modelMapper;

    private static final ModelMapper modelMapper1 = new ModelMapper();

    public String signup(Doctor doctor) {
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
        Doctor doctor1 = doctorRepository.findByUsernameOrEmail(dto.getUsernameOremail(),dto.getUsernameOremail()).get();
        boolean isPasswordMatch = passwordEncoder.matches(dto.getPassword(), doctor1.getPassword());
        if (isPasswordMatch) {
            doctorRepository.deleteById(doctor1.getDoctorId());
            return "Account is deleted";
        } else {
            throw new ResourceNotFoundExcecption("Invalid password Entered,Enter valid password");
        }
    }
    public homeview  logindoctoraccount(Logindto logindto) {
        Doctor doctor1 = doctorRepository.findByUsername(logindto.getUsernameOremail());
        if(doctor1 == null){
            throw new ResourceNotFoundExcecption("There is No account with UsernameOrEmail"+logindto.getUsernameOremail());
        } homeview homeview = new homeview();
        homeview returnhome = new homeview();
        if (doctor1 != null && logindto.getUsernameOremail().equals(doctor1.getEmail())  || logindto.getUsernameOremail().equals(doctor1.getUsername()) && passwordEncoder.matches(logindto.getPassword(), doctor1.getPassword())) {

            returnhome.setDoctorId("UNIQUE ID                :     "+doctor1.getDoctorId());
            returnhome.setName("NAME                         :     "+doctor1.getUsername());
            returnhome.setCreateSlot("CREATE AVALIABLE SLOTS :     "+homeview.getCreateSlot()+doctor1.getDoctorId()+"/yyyy-mm-dd");
            returnhome.setDeleteacount("DELETE ACCOUNT       :     "+homeview.getDeleteacount()+doctor1.getDoctorId());
            returnhome.setViewAvailableSlots("AVIALABLE SLOTS:     "+homeview.getViewAvailableSlots()+doctor1.getDoctorId());
            returnhome.setDeleteSlot("DELETE SLOTS           :     "+homeview.getDeleteSlot()+doctor1.getDoctorId()+"/yyyy-mm-dd");
            returnhome.setUpateProfile("UPDATE PROFILE       :     "+homeview.getUpateProfile()+doctor1.getDoctorId());
            returnhome.setUpdateSLot("UPDATE SOLT            :     "+homeview.getUpdateSLot()+doctor1.getDoctorId()+"/yyyy-mm-dd");
            returnhome.setDeleteacount("DELETE ACCOUNT       :     "+homeview.getDeleteacount()+doctor1.getDoctorId());
            returnhome.setProfileDetails("PROFILE DETAILS    :     "+homeview.getProfileDetails()+doctor1.getDoctorId());
            returnhome.setApppointmentlist("APPOINTMENTLIST   :    "+ homeview.getApppointmentlist()+doctor1.getDoctorId());
            returnhome.setReadAllreviews("READ REVIEWS        :     "+homeview.getReadAllreviews()+doctor1.getDoctorId());
            returnhome.setLogout("LOGOUT                       :    "+homeview.getLogout());
            return returnhome;
        }  if (doctor1 != null && passwordEncoder.matches(logindto.getPassword(), doctor1.getPassword())==false) {
                      throw new ResourceNotFoundExcecption("YOU HAVE ENTERED WRONG PASSWORD  ,IF YOU HAVE  FORGOTTEN YOUR PASSWORD ,`YOU CAN RESET PASSWORD HERE "+ "http://lcoalhost:8081/doctor/api/passwordreset/"+doctor1.getDoctorId());
        } else {
            throw new ResourceNotFoundExcecption("Invalid Credentials. Check entered email and password again");
        }
    }
    public Set<Doctordto> getalldoctors() {
        List<Doctor> all = doctorRepository.findAll();
//        List<Doctordto> fetcheddoctor = all.stream()
//                .map(doctor -> mapDoctorToDto(doctor))
//                .collect(Collectors.toList());
        double totalrating = 0;
        int count = 0;
        for ( Doctor doctor1 : all) {
            List<Reviews> byDoctor = reviewRepository.findByDoctorAccount(doctor1.getEmail());
            for(Reviews adddavgrating:byDoctor){
                totalrating = totalrating + adddavgrating.getRating();
                count++;
                    if(byDoctor.indexOf(adddavgrating)==byDoctor.size()-1){
                                double avgrating = 0;
                        avgrating = totalrating / count;
                        doctor1.setRating(avgrating);
                      }
            }
        }
        List<Doctordto> fetcheddoctor = all.stream()
                .map(doctor -> mapDoctorToDto(doctor))
                .collect(Collectors.toList());
        Set<Doctordto> returndto =new HashSet<>(fetcheddoctor);

//        double avgrating = 0;
//        avgrating = totalrating / count;
//        doctor1.setRating(avgrating);
//        List<Doctordto> fetcheddoctor = all.stream()
//                .map(doctor -> mapDoctorToDto(doctor)).distinct()
//                .collect(Collectors.toList());
//        Set<Doctor> returneddto = new HashSet<>();
//        for(Doctor  returndto:){
//            returneddto.add(returndto);
//        }
//        return returndto;
        return returndto;
    }
    public static Doctordto mapDoctorToDto(Doctor doctor) {
        return modelMapper1.map(doctor, Doctordto.class);
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


    public void savereview(Reviews reviews) {
        LocalDateTime todaydatetime = LocalDateTime.now();
        reviews.setDatetime(todaydatetime.toString());
        Reviews save = reviewRepository.save(reviews);

    }

    public String  resetpassword(String doctorId,Logindto logindto) {
        Doctor byDoctorId = doctorRepository.findByDoctorId(doctorId);
          Doctor doctor = new Doctor();
          doctor.setId(byDoctorId.getId());
          doctor.setUsername(byDoctorId.getUsername());
          doctor.setPhone(byDoctorId.getPhone());
          doctor.setLocation(byDoctorId.getLocation());
          doctor.setRating(byDoctorId.getRating());
          doctor.setExperience(byDoctorId.getExperience());
          doctor.setSignupdate(byDoctorId.getSignupdate());
          doctor.setPassword(passwordEncoder.encode(logindto.getPassword()));
          doctor.setQualification(byDoctorId.getQualification());
          doctor.setLocation(byDoctorId.getLocation());
          doctor.setEmail(byDoctorId.getEmail());
          doctor.setDoctorId(byDoctorId.getDoctorId());
          doctor.setFees(byDoctorId.getFees());
          doctor.setBookingCharge(byDoctorId.getBookingCharge());
          doctor.setSpecialization(byDoctorId.getSpecialization());
          doctor.setAvailableSlots(byDoctorId.getAvailableSlots());
          doctor.setBookingCharge(byDoctorId.getBookingCharge());
        Doctor save = doctorRepository.save(doctor);
        if(passwordEncoder.matches(logindto.getPassword(),save.getPassword())){
            return " Password is updated successfullly";
        }
        return "Something went wrong ,password not updated ";
    }
}
