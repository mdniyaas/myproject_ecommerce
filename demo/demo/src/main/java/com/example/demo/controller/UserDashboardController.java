package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserDashboardController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/user-dashboard")
    public String dashboard(Model model) {

        User user =
                userRepository.findById(1L).orElse(null);

        model.addAttribute("user", user);

        model.addAttribute(
                "orderCount",
                orderRepository.count()
        );

        model.addAttribute(
                "totalSpent",
                15000
        );

        return "user-dashboard";
    }

    @PostMapping("/update-profile")
    public String updateProfile(
            @ModelAttribute User user
    ) {

        userRepository.save(user);

        return "redirect:/user-dashboard";
    }

}