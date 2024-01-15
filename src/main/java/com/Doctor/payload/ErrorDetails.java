package com.Doctor.payload;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDetails {

    private Date date;

    private String messge;

    private String details;

    public ErrorDetails(Date date, String messge, String details) {
        this.date = date;
        this.messge = messge;
        this.details = details;
    }
}
