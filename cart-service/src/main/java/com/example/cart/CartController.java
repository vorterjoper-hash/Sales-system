package com.example.cart;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private List<String> cart = new ArrayList<>();

    @PostMapping("/{item}")
    public String addToCart(@PathVariable String item) {
        cart.add(item);
        return "Added " + item + " to cart";
    }

    @GetMapping
    public List<String> viewCart() {
        return cart;
    }
}
