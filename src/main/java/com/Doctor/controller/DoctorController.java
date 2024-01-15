package com.Doctor.controller;


import com.Doctor.entity.Doctor;

import com.Doctor.entity.Reviews;
import com.Doctor.payload.*;
import com.Doctor.payload.Docterdeletedto;
import com.Doctor.service.Doctorservice;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("doctor/api")
public class DoctorController {

//http://localhost:8081/doctor/service/createdoctoraccount
//    http://localhost:8081/doctor/service/doctorid

    @Autowired
    private Doctorservice doctorservice;

    @PostMapping
    public ResponseEntity<?> CreateDoctorId(@RequestBody @Valid  Doctor doctor){
//   try {
//       if (bindingResult.hasErrors()) {
//           return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//       }
       String createdoctoraccount = doctorservice.createdoctoraccount(doctor);
       return new ResponseEntity<>(createdoctoraccount, HttpStatus.CREATED);

   }
   @DeleteMapping("delete/{DoctorId}")
   public ResponseEntity<?> deletedoctoraccount(@PathVariable String DoctorId ,@RequestBody Logindto dto){
        doctorservice.deletedoctoraccount(DoctorId,dto);
        return  new ResponseEntity<>("Account Is Deleted",HttpStatus.OK);
   }

   @GetMapping
   public ResponseEntity<?> getalldoctors(){
      Set<Doctordto> getalldoctors = doctorservice.getalldoctors();
       return new ResponseEntity<>(getalldoctors,HttpStatus.OK);
   }


   @GetMapping("/{DoctorId}")
   public  ResponseEntity<?> getprofiledetails(@PathVariable String DoctorId){
       Doctor gatprofiledetails = doctorservice.gatprofiledetails(DoctorId);
       return new ResponseEntity<>(gatprofiledetails,HttpStatus.OK);
   }

   @PutMapping("update/{DoctorId}")
   public ResponseEntity<?> updateaccount( @PathVariable String DoctorId,@RequestBody @Valid  Doctor doctor){
       String updatedoctoraccoutn = doctorservice.updatedoctoraccoutn(DoctorId, doctor);
       return new ResponseEntity<>(updatedoctoraccoutn,HttpStatus.OK);
   }




//   catch (Exception e){
//       throw  new RuntimeException("Something went wrong");
//   }


//    @PostMapping("/signin")
//    ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword());
//        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("User signed-in successfully!.",
//                HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> logindoctoraccount(@RequestBody Logindto logindto){
        homeview logindoctoraccount =doctorservice.logindoctoraccount(logindto);
        return new ResponseEntity<>(logindoctoraccount,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getalldoctos(){
        Set<Doctordto> getalldoctors = doctorservice.getalldoctors();
        return new ResponseEntity<>(getalldoctors,HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<?> review(@RequestBody Reviews reviews){
        doctorservice.savereview(reviews);
        return new ResponseEntity<>("", HttpStatus.OK);
    }






}
