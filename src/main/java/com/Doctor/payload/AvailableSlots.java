package com.Doctor.payload;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class AvailableSlots {
  private  String doctorId;
   private String book;
    private String date;
    private boolean morningSlot;
    private boolean afternoonSlots;
    private boolean eveningSlots;

}
