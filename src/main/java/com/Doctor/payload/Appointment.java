package com.Doctor.payload;

import com.Doctor.entity.Doctor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class Appointment {

    private LocalDate datetme;

    private Patient patient;

    private Doctor doctor;


}
