package com.example.cart;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.util.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private List<String> cart = new ArrayList<>();
    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/{item}")
    public String addToCart(@PathVariable String item) {
        cart.add(item);

        String inventoryUrl = "http://inventory-service:8080/inventory/deduct/" + item;
        try {
            String inventoryResponse = restTemplate.postForObject(inventoryUrl, null, String.class);
            return "Added " + item + " to cart. Inventory says: " + inventoryResponse;
        } catch (ResourceAccessException e) {
            // Happens if the service is unreachable / refused connection
            System.err.println("❌ Cannot connect to Inventory service at " + inventoryUrl);
            return "Added " + item + " to cart, but could not reach Inventory service.";
        } catch (RestClientException e) {
            // Any other client-side error
            System.err.println("⚠️ Error calling Inventory service: " + e.getMessage());
            return "Added " + item + " to cart, but inventory call failed: " + e.getMessage();
        }
    }

    @GetMapping
    public List<String> viewCart() {
        return cart;
    }
}