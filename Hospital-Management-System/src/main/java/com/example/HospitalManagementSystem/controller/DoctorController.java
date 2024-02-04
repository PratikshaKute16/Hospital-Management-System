package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.entity.Appointment;
import com.example.HospitalManagementSystem.entity.Prescription;
import com.example.HospitalManagementSystem.service.AppointmentService;
import com.example.HospitalManagementSystem.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/")
    public String doctorHome(Model model) {
        // Assuming you have a method in your service to get appointments for the logged-in doctor
        List<Appointment> appointments = appointmentService.getAllAppointments(getLoggedInDoctorUsername());

        model.addAttribute("appointments", appointments);

        return "doctor/home";
    }

    // Utility method to get the username of the currently logged-in doctor
    private String getLoggedInDoctorUsername() {
        // Implement this method based on your authentication mechanism
        // For example, if you are using Spring Security:
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable int id, RedirectAttributes redirectAttributes) {
        // Check if the appointment exists
        Appointment existingAppointment = appointmentService.getAppointmentById(id);

        if (existingAppointment != null) {
            // Mark the appointment as deleted
            appointmentService.updateAppointment(id, null, true);

            // Add a success message to FlashAttributes
            redirectAttributes.addFlashAttribute("successMessage", "Appointment deleted successfully!");
//
        }

        // Redirect to the doctor's home page after deletion
        return "redirect:/doctor/";
    }

    @GetMapping("/get/{id}")
    public String getPrescriptionForm(@PathVariable int id, Model model) {
        try {
            // Log the id to check if it's received correctly
            System.out.println("Received ID: " + id);

            // Logic to fetch prescription by id and add it to the model
            Appointment existingAppointment = appointmentService.getAppointmentById(id);

            if (existingAppointment != null) {
                model.addAttribute("prescription", existingAppointment);
                return "doctor/prescription";
            } else {
                // Log that prescription is not found
                System.out.println("Prescription not found for ID: " + id);
                // Handle the case where prescription is not found
                return "redirect:/error";
            }
        } catch (Exception e) {
            // Log or handle the exception as needed
            e.printStackTrace();
            return "redirect:/error";
        }
    }


}


