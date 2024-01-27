package com.Doctor.repository;

import com.Doctor.entity.Doctor;
import com.Doctor.payload.AvailableSlots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public  interface DoctorRepository extends MongoRepository<Doctor,String> {

    boolean existsByEmail(String Email);
    boolean existsByPhone(long Phone);
      Doctor findByAvailableSlots(String Date);
    Optional<Doctor> findByUsernameOrEmail(String username, String email);

     Doctor findByEmail(String email);
    Doctor findByDoctorId(String doctorId);
    Doctor  findByUsername(String username);
}
