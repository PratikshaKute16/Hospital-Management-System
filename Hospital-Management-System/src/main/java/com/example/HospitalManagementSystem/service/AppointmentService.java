package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.entity.Appointment;
import com.example.HospitalManagementSystem.repository.AppointmentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService  {

    private AppointmentRepo appointmentRepo;

    @Autowired
    public AppointmentService(AppointmentRepo appointmentRepository) {
        appointmentRepo = appointmentRepository;
    }
//new added
    public Appointment saveDepartment(Appointment department) {

        return appointmentRepo.save(department);
    }

    public List<Appointment> fetchDepartment() {
        return appointmentRepo.findAll();
    }

    public void deleteById(int id) {
        appointmentRepo.deleteById(id);
    }




    public List<Appointment> getAllAppointments(String loggedInDoctorUsername) {

        return appointmentRepo.findByDeletedFalse();
    }

    //new change
    


    public Appointment getAppointmentById(int id) {
        Optional<Appointment> optionalAppointment = appointmentRepo.findById(id);
        return optionalAppointment.orElse(null);
    }


    public Appointment createAppointment(Appointment appointment) {

        return appointmentRepo.save(appointment);
    }


    @Transactional
    public Appointment updateAppointment(int id, Appointment newAppointment, boolean markAsDeleted) {
        Optional<Appointment> optionalExistingAppointment = appointmentRepo.findById(id);

        if (optionalExistingAppointment.isPresent()) {
            Appointment existingAppointment = optionalExistingAppointment.get();

            // Logging for debugging
            System.out.println("Mark as deleted: " + markAsDeleted);
            System.out.println("Existing appointment before modification: " + existingAppointment);

            // Optionally mark as deleted
            if (markAsDeleted) {
                existingAppointment.setDeleted(true);
                existingAppointment.setPrescribe(true);
            } else {
                // Update fields as needed
                existingAppointment.setFirstname(newAppointment.getFirstname());
                // ... (other fields)
                existingAppointment.setDeleted(false); // Ensure it's not marked as deleted
                existingAppointment.setPrescribe(false);
            }

            // Logging after modification
            System.out.println("Existing appointment after modification: " + existingAppointment);

            return appointmentRepo.save(existingAppointment);
        } else {
            return null; // or throw an exception, depending on your requirements
        }
    }


    public boolean deleteAppointment(int id) {
        Optional<Appointment> optionalAppointment = appointmentRepo.findById(id);

        if (optionalAppointment.isPresent()) {
            appointmentRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
