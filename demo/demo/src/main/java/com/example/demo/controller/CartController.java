package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    // ADD TO CART (FIXED)
    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, Principal principal) {

        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {

            Cart cart = new Cart();
            cart.setUsername(principal.getName());
            cart.setProductName(product.getName());
            cart.setPrice(product.getPrice());
            cart.setQuantity(1);
            cart.setImageUrl(product.getImageUrl());

            cartRepository.save(cart);
        }

        return "redirect:/cart";
    }

    // VIEW CART
    @GetMapping("/cart")
    public String cart(Model model, Principal principal) {

        List<Cart> cartItems =
                cartRepository.findByUsername(principal.getName());

        double total = 0;

        for (Cart c : cartItems) {
            total += c.getPrice() * c.getQuantity();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "cart";
    }

    // DELETE ITEM
    @GetMapping("/cart/delete/{id}")
    public String deleteCart(@PathVariable Long id) {
        cartRepository.deleteById(id);
        return "redirect:/cart";
    }
}