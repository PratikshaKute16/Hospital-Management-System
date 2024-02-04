package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.entity.Appointment;
import com.example.HospitalManagementSystem.entity.Prescription;
import com.example.HospitalManagementSystem.service.AppointmentService;
import com.example.HospitalManagementSystem.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PrescriptionService prescriptionService;


    @GetMapping("/show")
    public String showPrescription()
    {
        return "doctor/prescription";
    }


    @PostMapping("/submit")
    public String submitPrescription(@Validated @ModelAttribute("prescription") Prescription prescription,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Binding errors: " + result.getAllErrors());
            return "doctor/prescription";
        }
        prescriptionService.savePrescription(prescription);
        System.out.println("successfully submitted");
        // You can add additional logic or redirect to another page after successful submission
        return "redirect:/prescription/show";


    }

    //chat gpt


}


