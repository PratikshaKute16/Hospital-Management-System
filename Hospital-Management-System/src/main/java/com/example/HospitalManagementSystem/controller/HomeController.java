package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.entity.Appointment;
import com.example.HospitalManagementSystem.entity.UserDetail;
import com.example.HospitalManagementSystem.service.UserService;
//import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "aboutUs";
    }



    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserDetail user, HttpSession session) {
//        System.out.println(user);
        boolean b = userService.checkEmail(user.getEmail());
        if (b) {
//            System.out.println("email is already exist");
            session.setAttribute("msg","Email ID is already exists");
//            session.removeAttribute("msg");
        } else {
            UserDetail details = userService.createUser(user);
            if (details != null) {
//                System.out.println("Register Successfully");
                session.setAttribute("msg","Register Successfully!");
            } else {
//                System.out.println("Something error in Server");
                session.setAttribute("msg","Something Wrong on Server");
            }

        }
        return "redirect:/register";
    }
}