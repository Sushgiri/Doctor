package com.Doctor.entity;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.Doctor.payload.Review;
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



    @Field(name = "userid")
    private String id;

    @Field(name = "UserName")
    private String name;
    @Field(name = "UserEmail")
    private String email;

    @Field(name = "Rating")
    private double rating ;

    @Field(name = "Content")
    private String content;

    @Field(name = "Doctor")
    private String doctorAccount;

    @Field(name = "DateTime")
    private String Datetime;





}
