package com.example.inventory;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private Map<String, Integer> stock = new HashMap<>(Map.of("Laptop", 10, "Phone", 20));

    @PostMapping("/deduct/{item}")
    public String deductStock(@PathVariable String item) {
        stock.put(item, stock.getOrDefault(item, 0) - 1);
        return "Stock updated for " + item + ": " + stock.get(item);
    }

    @GetMapping
    public Map<String, Integer> getStock() {
        return stock;
    }
}
