package com.Doctor.payload;

import lombok.Data;

@Data

public class homeview {
    String doctorId;
     String Name ;
    String  DeleteSlot="http://localhost:8081/doctor/available/api/";
    String CreateSlot ="http://localhost:8081/doctor/available/api/";
    String UpdateSLot="http://localhost:8081/doctor/available/api/doctorId/date";
    String ProfileDetails=" http://localhost:8081/doctor/api/";
    String ViewAvailableSlots ="http://localhost:8081/doctor/available/api/";
    String UpateProfile =" http://localhost:8081/doctor/api/update/";
    String Deleteacount=" http://localhost:8081/doctor/api/delete/";

}
