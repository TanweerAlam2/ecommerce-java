package com.example.marketly.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String q,
                       @RequestParam(required = false) String category,
                       Model model) {
        List<Product> products;
        if (q != null && !q.isBlank()) {
            products = repository.findByNameContainingIgnoreCase(q);
        } else if (category != null && !category.isBlank()) {
            products = repository.findByCategoryIgnoreCase(category);
        } else {
            products = repository.findAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("query", q == null ? "" : q);
        model.addAttribute("selectedCategory", category == null ? "" : category);
        return "index";
    }

    @GetMapping("/products/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("product", repository.findById(id).orElseThrow());
        return "product-detail";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/account")
    public String account() {
        return "account";
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/api/products")
    @ResponseBody
    public List<Product> apiProducts() {
        return repository.findAll();
    }
}
