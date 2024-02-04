package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.entity.Appointment;
import com.example.HospitalManagementSystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;


@Validated
@Controller
public class AppointmentController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/user/appointment")
    public String showAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "user/appointment";
    }


    @PostMapping("/user/appointment")
    public String submitAppointmentForm(@Validated @ModelAttribute("appointment") Appointment appointment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Binding errors: " + result.getAllErrors());
            return "user/appointment";
        }

        try {
            // Print the date from the form to debug
            System.out.println("Date from form: " + appointment.getDate());
            System.out.println("Time from form: " + appointment.getTime());
        appointment.setDeleted(false);
            appointment.setPrescribe(false);
            // Continue with your existing code
            appointmentService.createAppointment(appointment);
            model.addAttribute("message", "Appointment submitted successfully!");
            return "redirect:/user/appointment/success";
        } catch (Exception e) {
            result.rejectValue("date", "date.format", "Invalid date or time format");
            return "user/appointment";
        }
    }
         // Return to the form page with an error message






    @GetMapping("user/appointment/success")
    public String showSuccessPage() {
        return "user/success"; // Assuming there's an HTML template at src/main/resources/templates/success.html
    }
}
