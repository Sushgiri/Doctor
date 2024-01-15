package com.Doctor.service;

import com.Doctor.payload.AvailableSlots;
import com.Doctor.entity.Doctor;
import com.Doctor.exception.ResourceNotFoundExcecption;

import com.Doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Availableservice {



    @Autowired
    private DoctorRepository doctorRepository;


    public String creatavailableslots(String DoctorId, AvailableSlots availableSlots) {
        Doctor byDoctorId = doctorRepository.findByDoctorId(DoctorId);
        if (byDoctorId == null) {
            throw new ResourceNotFoundExcecption("There is no Doctor Account with id" + DoctorId);
        }
        LocalDate currentDate = LocalDate.now();
        String todaydate = currentDate.toString();
        int comparison = todaydate.compareTo(availableSlots.getDate());
        if (comparison < 0) {
            Set<AvailableSlots> uniqueslots = byDoctorId.getAvailableSlots();
            int sizebeforeinsertion = uniqueslots.size();
            if (uniqueslots.size() < 4) {
                uniqueslots.add(availableSlots);
                byDoctorId.setAvailableSlots(uniqueslots);
                doctorRepository.save(byDoctorId);
                Set<AvailableSlots> afterinsert = byDoctorId.getAvailableSlots();
                int sizeafterinsertion = afterinsert.size();
                if (sizeafterinsertion == sizebeforeinsertion) {
                    return "slots are already Inserted for this date ,you can update records here" + "http://lcoalhost:8081/doctor/available/api//" + byDoctorId.getDoctorId() + "/" + availableSlots.getDate();
                } else {
                    return " Available Slots for " + byDoctorId.getDoctorName() + " inserted for Date " + availableSlots.getDate();
                }
            } else {
                return "Slots are Inserted for Four days ,you can't insert data of more than  Four days";
            }
        } else {
            return "Invalid Date Entered";
        }
    }
    public String updateslots(String doctorId, String date, AvailableSlots availableSlots) {
        Doctor byDoctorId = doctorRepository.findByDoctorId(doctorId);
        if (byDoctorId != null) {
            Set<AvailableSlots> fetchedSlots = byDoctorId.getAvailableSlots();
            boolean found = false;
            for (AvailableSlots slot : fetchedSlots) {
                if (slot.getDate().equals(date)) {
                    slot.setMorningSlot(availableSlots.isMorningSlot());
                    slot.setAfternoonSlots(availableSlots.isAfternoonSlots());
                    slot.setEveningSlots(availableSlots.isEveningSlots());
                    found = true;
                    break;
                }
            }
            if (found == false) {
                return "No Record Found for date  " + date;
            }
            byDoctorId.setAvailableSlots(fetchedSlots);
            doctorRepository.save(byDoctorId);
            return "Slots Updated for Date  " + date;
        }
        return "There is no doctor with id" + doctorId;
    }


    public String deleteslots(String doctorId, String date) {
        Doctor byDoctorId = doctorRepository.findByDoctorId(doctorId);

        if (byDoctorId != null) {
            Set<AvailableSlots> fetchedSlots = byDoctorId.getAvailableSlots();
            boolean found = false;
            for (AvailableSlots slot : fetchedSlots) {
                if (slot.getDate().equals(date)) {
                    fetchedSlots.remove(slot);
                    found = true;
                    break;
                }
            }
            byDoctorId.setAvailableSlots(fetchedSlots);
            doctorRepository.save(byDoctorId);
        }
        return "SLots Removed for "+date;
    }
 public Set<AvailableSlots>  getslotsbyid(String DoctorId){
     Doctor byDoctorId = doctorRepository.findByDoctorId(DoctorId);
     Set<AvailableSlots> fetchedslots = byDoctorId.getAvailableSlots();
     return fetchedslots;
 }
}