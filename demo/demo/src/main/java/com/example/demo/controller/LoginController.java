package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    // @GetMapping("/dashboard")
    // public String adminDashboard() {

    // return "dashboard";
    // }

    // @GetMapping("/user-dashboard")
    // public String userDashboard() {

    // return "user-dashboard";
    // }
}