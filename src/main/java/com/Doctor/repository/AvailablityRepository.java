package com.Doctor.repository;

import com.Doctor.payload.AvailableSlots;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AvailablityRepository extends  MongoRepository<AvailableSlots,String> {
    boolean existsByDate(String Date);
}
