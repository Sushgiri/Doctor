package com.Doctor.payload;

import lombok.Data;

@Data
public class Appointmentdto {

    private String Appointmentdate;

    private String doctorId;



    private String bookingdate;
    private String patientname;
    private String patientage;
    private String patientId;
    private boolean medicalhistory;
    private String patientcontact;
    private  boolean bookingchargepaid;
    private boolean  ActiveAppointment;

}
