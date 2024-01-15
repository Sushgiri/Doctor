package com.Doctor.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "DoctorReview")
public class Reviews {

    @Id
    private String  ReviewId;
    @Field("Name")
    @NotEmpty(message = "name should not be empty")
    private String Name;

    @Field("UserEmail")
    @NotEmpty(message = "Email should not be empty")
    private String Email;


    @Field("Ratings")
    @NotNull(message = "How much do you rate a doctor")
    private  double Rating;
    @Field("Email")
    @Size(min = 5)
    @NotEmpty(message = "Describe your Experience in few Words")
    private String Description;


    @Field("DoctorId")
    private String DoctorId;



}
