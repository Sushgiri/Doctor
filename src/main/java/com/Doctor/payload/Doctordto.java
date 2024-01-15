//package com.Doctor.payload;
//
//import com.Doctor.payload.AvailableSlots;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//import javax.persistence.Id;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.List;
//
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Doctordto {
//
//
//
//    @NotEmpty(message = "name should not be empty")
//    private String DoctorName;
//
//    @NotEmpty(message = "Qualification should not be empty")
//    private String Qualification;
//
//    @NotEmpty(message = "Specialization should not be empty")
//    private String Specialization;
//
//    @NotEmpty(message = "Experience should not be empty")
//   private  String Experience;
//
//    @NotNull(message = "Enter Ypur  Fees")
//    private  int Fees;
//
//    @NotNull(message = "Enter Yorn Booking Charge")
//    private double BookingCharge;
//
//
//    @NotEmpty(message = "Enter Your location ")
//    private  String location;
//    @Size(max = 12,min = 10,message = "Incorrect Phone Number")
//    @NotEmpty(message = "Enter your phone details")
//    private int phone;
//    @NotEmpty(message = "Enter your Email details")
//    private String Email;
//
//
//
//    @NotEmpty(message = "Password could not be empty")
//    @Size(min = 8, max=8,message = "password should be of exactly 8 charcates")
//    private String Password;
//    private double Rating;
//
//
////   private List<AvailableSlots> availableSlots;
////
////    private List<Appointment> Appointment;
////
////
////    List<com.Doctor.entity.Reviews> Reviews;
//
//}
