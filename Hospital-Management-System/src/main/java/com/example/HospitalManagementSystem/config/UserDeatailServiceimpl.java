package com.example.HospitalManagementSystem.config;

import com.example.HospitalManagementSystem.entity.UserDetail;
import com.example.HospitalManagementSystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDeatailServiceimpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetail userDetail=userRepo.findByEmail(email);
                if(userDetail!=null)
                {
                    return new CustomUserDetails(userDetail);
                }
        throw new UsernameNotFoundException("User not available");
    }
}
