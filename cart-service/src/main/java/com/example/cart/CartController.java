package com.example.cart;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private List<String> cart = new ArrayList<>();
    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/{item}")
    public String addToCart(@PathVariable String item) {
        cart.add(item);

        // Call inventory-service (Docker DNS name) to update stock
        String inventoryUrl = "http://inventory-service:8080/inventory/deduct/" + item;
        String inventoryResponse = restTemplate.postForObject(inventoryUrl, null, String.class);

        return "Added " + item + " to cart. Inventory says: " + inventoryResponse;
    }

    @GetMapping
    public List<String> viewCart() {
        return cart;
    }
}