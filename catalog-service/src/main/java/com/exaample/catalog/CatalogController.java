package com.example.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class CatalogController {
    @GetMapping("/items")
    public List<Map<String, String>> getItems() {
        return List.of(
            Map.of("id", "1", "name", "Laptop", "price", "1000"),
            Map.of("id", "2", "name", "Phone", "price", "500")
        );
    }
}