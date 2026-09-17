package com.example.demo.controller;

import com.example.demo.entity.OrderEntity;
import com.example.demo.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // VIEW ALL ORDERS
    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    // VIEW SINGLE ORDER DETAILS
    @GetMapping("/order/{id}")
    public String orderDetails(@PathVariable Long id, Model model) {

        OrderEntity order = orderRepository.findById(id).orElse(null);

        model.addAttribute("order", order);
        return "order-details";
    }

    // CANCEL ORDER
    @GetMapping("/order/cancel/{id}")
    public String cancelOrder(@PathVariable Long id) {

        OrderEntity order = orderRepository.findById(id).orElse(null);

        if (order != null && !"DELIVERED".equals(order.getStatus())) {
            order.setStatus("CANCELLED");
            orderRepository.save(order);
        }

        return "redirect:/orders";
    }

    // EDIT ADDRESS PAGE
    @GetMapping("/order/edit/{id}")
    public String editOrderPage(@PathVariable Long id, Model model) {

        model.addAttribute("order",
                orderRepository.findById(id).orElse(null));

        return "edit-order";
    }

    // UPDATE ADDRESS
    @PostMapping("/order/update")
    public String updateOrder(@ModelAttribute OrderEntity order) {

        OrderEntity existing = orderRepository.findById(order.getId()).orElse(null);

        if (existing != null) {
            existing.setAddress(order.getAddress());
            orderRepository.save(existing);
        }

        return "redirect:/orders";
    }
}