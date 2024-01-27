package com.Doctor.controller;

import com.Doctor.config.ResttemplateConfig;
import com.Doctor.payload.Appointmentdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("doctor/appointment")
public class Appointmentcontroller {
//http://localhost:8081/doctor/appointment/
    @Autowired
    private ResttemplateConfig restTemplate;

    @GetMapping("/{DoctorId}")
    public ResponseEntity<?> getallappointments(@PathVariable String DoctorId ){
        Appointmentdto[] allappointment = restTemplate.getRestTemplate().getForObject("http://localhost:8080/doctor/appointemnt/doctor/"+DoctorId, Appointmentdto[].class);
         List<Appointmentdto> appointmentdtoList = Arrays.stream(allappointment).collect(Collectors.toList());;
          if(allappointment == null){
              return new ResponseEntity<>("No Active appointments",HttpStatus.OK);
          }
        return new ResponseEntity<>(appointmentdtoList, HttpStatus.OK);
    }
}
