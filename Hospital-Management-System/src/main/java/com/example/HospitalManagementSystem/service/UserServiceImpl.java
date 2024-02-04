package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.entity.UserDetail;
import com.example.HospitalManagementSystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetail createUser(UserDetail details) {
        details.setPassword(passwordEncoder.encode(details.getPassword()));
        details.setRole("ROLE_USER");
        return userRepo.save(details);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }
}
