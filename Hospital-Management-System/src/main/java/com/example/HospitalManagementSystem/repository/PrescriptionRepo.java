package com.example.HospitalManagementSystem.repository;

import com.example.HospitalManagementSystem.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription,Integer> {

}
