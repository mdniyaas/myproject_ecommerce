package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Cart;
import com.example.demo.entity.OrderEntity;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;

@Controller
public class CheckoutController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/checkout")
    public String checkout(Model model,
                           Principal principal) {

        List<Cart> cartItems =
                cartRepository.findByUsername(
                        principal.getName());

        double total = 0;

        for(Cart c : cartItems) {
            total += c.getPrice() * c.getQuantity();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(Principal principal) {

        List<Cart> cartItems =
                cartRepository.findByUsername(
                        principal.getName());

        for(Cart c : cartItems) {

            OrderEntity order =
                    new OrderEntity(
                            principal.getName(),
                            c.getProductName(),
                            null, c.getPrice(),
                            c.getQuantity(),
                            "Order Placed", null
                    );

            orderRepository.save(order);
        }

        cartRepository.deleteAll(cartItems);

        return "redirect:/orders";
    }
}