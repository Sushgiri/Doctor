package com.Doctor.repository;

import com.Doctor.entity.Reviews;
import com.Doctor.payload.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ReviewRepository extends  MongoRepository<Reviews,String> {
      List<Reviews> findByDoctorAccount(String email);
}
