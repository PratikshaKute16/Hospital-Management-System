package com.example.HospitalManagementSystem.repository;

import com.example.HospitalManagementSystem.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDetail,Integer>
{
       public  boolean existsByEmail(String email);
       public UserDetail findByEmail(String email);
}
