package com.example.HospitalManagementSystem.repository;

import com.example.HospitalManagementSystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("appointmentRepository")
public interface AppointmentRepo extends JpaRepository<Appointment,Integer>
{
    List<Appointment> findByDeletedFalse();
}
