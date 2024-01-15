package com.Doctor.entity;

import com.Doctor.payload.Appointment;
import com.Doctor.payload.AvailableSlots;
import com.Doctor.payload.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.reflect.ReflectionVar;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Doctors")

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
       private  String id;
    @Field("DoctorId")
    private String  doctorId;
    @Field("Doctor")
    @NotBlank(message = "Name Cant be Empty")
    private String doctorName;

    @Field("phone")
    @NotBlank(message = "Phone cant be Empty")
    private  long phone;

    @Field("Email")
    @NotBlank(message = "Email cant be Empty")
    private String email;


    @Field("Qualification")
    @NotBlank(message = "Enter Your Qualification")
    private String qualification;

    @Field("Specialization")
    @NotBlank(message = "Enter Specialization")
    private String specialization;

    @Field("Experience")
    @NotBlank(message = "Enter your working Experience")
   private  String experience;

    @Field("Fees")
    @NotBlank(message = "Enter Your Fees")
    private  int fees;

    @Field("BookingCharge")
    @NotBlank(message = "Enter Booking Charges")
    private double bookingCharge;

@Field("Location")
@NotBlank(message = "Enter Your location")
    private  String location;

    @Field("Rating")
    private double rating;


    @Field("Password")
    @NotBlank(message = "Enter a password of 8 characters")
    @Size(max = 8,min = 8,message = "password should be exactly of 8 characters")
    private String password;

    @Field("AccountCreatedDate")
private String signupdate;
//    @Field("AccountCreatationTime")
//    private LocalTime signuptime;
//

    @Field("AvailableSlots")
   private Set<AvailableSlots> availableSlots;


    private List<Reviews> reviews;

    private List<Appointment> Appointment;
//
//   @Field("Review")
//    List<Reviews> Reviews;

}
