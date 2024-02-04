package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.entity.Prescription;
import com.example.HospitalManagementSystem.repository.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepo prescriptionRepo;


    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepo.save(prescription);
    }

    public Optional<Prescription> findById(Integer id) {
        return prescriptionRepo.findById(id);
    }
}
