package com.Doctor.service;//package com.blogger.service.impl;
////
////import com.blogger.entity.Role;
////import com.blogger.entity.User;
////import com.blogger.repository.UserRepository;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.core.userdetails.UserDetails;
//import com.blogger.entity.Role;
//import com.blogger.entity.User;
//import com.blogger.repository.UserRepository;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import com.Doctor.entity.Doctor;
import com.Doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private DoctorRepository doctorRepository;
    public CustomUserDetailsService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws
            UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByUsernameOrEmail(usernameOrEmail,
                        usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));

        return new org.springframework.security.core.userdetails.User(doctor.getEmail(), doctor.getPassword(),Collections.emptyList() );
    }

}
