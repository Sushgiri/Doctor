package com.Doctor.payload;//package com.Doctor.payload;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctordto {
    private  String doctorId;
    private String doctorName;
    private String specialization;
    private String qualification;
    private  String experience;
    private  long phone;
    private String email;
    private  int fees;
    private double bookingCharge;
    private  String location;
    private double rating;
    private Set<AvailableSlots> availableSlots;

}