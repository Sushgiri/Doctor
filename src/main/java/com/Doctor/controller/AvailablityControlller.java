package com.Doctor.controller;

import com.Doctor.entity.Doctor;
import com.Doctor.entity.Reviews;
import com.Doctor.payload.AvailableSlots;
import com.Doctor.repository.DoctorRepository;
import com.Doctor.repository.ReviewRepository;
import com.Doctor.service.Availableservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("doctor/available/api")
public class AvailablityControlller {

    @Autowired
    private Availableservice availableservice;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private DoctorRepository doctorRepository;


    @PostMapping("/{DoctorId}")
    public ResponseEntity<?> createAvailableslotsbydate(@PathVariable String DoctorId, @RequestBody AvailableSlots availableSlots){
        String creatavailableslots = availableservice.creatavailableslots(DoctorId, availableSlots);
        return new ResponseEntity<>(creatavailableslots, HttpStatus.OK);
    }
    // http://localhost:8081/doctor/available/api/DoctorId/date
    @PutMapping("/{DoctorId}/{date}")
    public ResponseEntity<?> updateSoltsByDate(@PathVariable String DoctorId,@PathVariable String date,@RequestBody AvailableSlots availableSlots ){
        String updateslots = availableservice.updateslots(DoctorId, date, availableSlots);
        return new ResponseEntity<>("Updated    "+updateslots,HttpStatus.OK);
    }


    @DeleteMapping("/{DoctorId}/{date}")
    public ResponseEntity<?> deleteslots(@PathVariable String DoctorId,@PathVariable String date){

        String deleteslots = availableservice.deleteslots(DoctorId, date);
        return new ResponseEntity<>(deleteslots,HttpStatus.OK);
    }

    //http://localhost:8081/doctor/available/api/
    @GetMapping("/{DoctorId}")
    public ResponseEntity<?> getslotsbyId(@PathVariable String DoctorId){
        Set<AvailableSlots> getslotsbyid = availableservice.getslotsbyid(DoctorId);
        return new ResponseEntity<>(getslotsbyid,HttpStatus.OK);
    }
   // http://localhost:8081/doctor/available/api/review
//    @GetMapping("/review/{DoctorId}")
//    public  ResponseEntity<?>  getaallreviewsbydoctorId(@PathVariable String DoctorId){
//        Doctor byDoctorId = doctorRepository.findByDoctorId(DoctorId);
//        List<Reviews> byDoctorAccount = reviewRepository.findByDoctorAccount(byDoctorId.getEmail());
//        return  new ResponseEntity<>(byDoctorAccount,HttpStatus.OK);
//
//    }



}
