package com.example.demo.controller;
import org.springframework.ui.Model;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Product;
import com.example.demo.entity.Wishlist;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.WishlistRepository;

@Controller
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

@GetMapping("/wishlist/add/{id}")
    public String addToWishlist(
            @PathVariable Long id,
            Principal principal) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        Wishlist w = new Wishlist();

        w.setProductId(product.getId());
        w.setProductName(product.getName());
        w.setPrice(product.getPrice());
        w.setUsername(principal.getName());

        wishlistRepository.save(w);

        return "redirect:/wishlist";
    }



    @GetMapping("/wishlist")
public String wishlist(Model model,
                       Principal principal) {

    model.addAttribute(
            "wishlistItems",
            wishlistRepository.findByUsername(
                    principal.getName()));

    return "wishlist";
}

@GetMapping("/wishlist/remove/{id}")
public String removeWishlistItem(@PathVariable Long id) {

    wishlistRepository.deleteById(id);

    return "redirect:/wishlist";
}

}