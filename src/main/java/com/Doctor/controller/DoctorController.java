package com.Doctor.controller;


import com.Doctor.entity.Doctor;

import com.Doctor.entity.Reviews;
import com.Doctor.payload.*;
import com.Doctor.payload.Docterdeletedto;
import com.Doctor.security.JwtTokenProvider;
import com.Doctor.service.Doctorservice;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Doctor doctor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(doctor == null){
            return  new
                    ResponseEntity<>("Please Enter Your Details to register an account",HttpStatus.OK);
        }
        doctorservice.signup(doctor);
        return new ResponseEntity<>("User Registered Successfully",HttpStatus.OK);
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
  //http://localhost:8081/doctor/api/passwordreset
    @PostMapping("/login")
    public ResponseEntity<?> logindoctoraccount(@RequestBody Logindto logindto){
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(
                logindto.getUsernameOremail(), logindto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        homeview logindoctoraccount =doctorservice.logindoctoraccount(logindto);
        String token = tokenProvider.generateToken(authentication);
        return new ResponseEntity<>(logindoctoraccount +"\n"+"JWT TOKEN :  "+token,HttpStatus.OK);
    }
    @PutMapping("/passwordreset/{DoctorId}")
    public  ResponseEntity<?> resetpassword(@PathVariable String DoctorId,@RequestBody Logindto logindto){
        String resetpassword = doctorservice.resetpassword(DoctorId,logindto);
        return new ResponseEntity<>(resetpassword,HttpStatus.OK);
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
    @GetMapping("/logout")
    public ResponseEntity<String> logoutaccount() {
        Authentication authentiaction = SecurityContextHolder.getContext().getAuthentication();
        if (authentiaction != null) {
            //Account will logged out and JWT Token is Expired
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return new ResponseEntity<>("Logged Out Successfully", HttpStatus.OK);
    }
}
