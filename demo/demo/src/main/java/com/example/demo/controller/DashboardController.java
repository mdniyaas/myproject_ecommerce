package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

import java.util.List;
import com.example.demo.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        // TOTAL PRODUCTS

        long productCount =
                productRepository.count();

        // TOTAL EMPLOYEES

        long employeeCount =
                employeeRepository.count();

        // TOTAL ORDERS

        long orderCount =
                orderRepository.count();

List<Product> lowStock =
            productRepository.findByQuantityLessThan(5);

        // TOTAL REVENUE

        Double totalRevenue =
                orderRepository.getTotalRevenue();

        if(totalRevenue == null){
            totalRevenue = 0.0;
        }

        // SEND TO FRONTEND

        model.addAttribute("productCount",
                productCount);

        model.addAttribute("employeeCount",
                employeeCount);

        model.addAttribute("orderCount",
                orderCount);

        model.addAttribute("totalRevenue",
                totalRevenue);
model.addAttribute("monthlySales",
        new int[]{12,19,8,15,22,30});

model.addAttribute("orders",
        orderRepository.findAll());
        model.addAttribute("lowStock", lowStock);

        return "dashboard";
    }
}