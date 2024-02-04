package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.entity.UserDetail;

public interface UserService
{
   public UserDetail createUser(UserDetail details);
   public  boolean checkEmail(String email);

    Object findAllUser();

    void deleteUser(long parseLong);
}
