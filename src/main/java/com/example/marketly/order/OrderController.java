package com.example.marketly.order;

import com.example.marketly.product.Product;
import com.example.marketly.product.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository orders;
    private final ProductRepository products;

    public OrderController(OrderRepository orders, ProductRepository products) {
        this.orders=orders; this.products=products;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody OrderRequest request) {
        Order order = new Order();
        order.setCustomerName(request.customerName());
        order.setEmail(request.email());
        BigDecimal total = BigDecimal.ZERO;
        for (OrderRequest.Item item : request.items()) {
            Product product = products.findById(item.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + item.productId()));
            if (item.quantity() > product.getStock()) throw new IllegalArgumentException("Insufficient stock for " + product.getName());
            order.getItems().add(new OrderItem(product.getId(), product.getName(), product.getPrice(), item.quantity()));
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(item.quantity())));
            product.setStock(product.getStock() - item.quantity());
            products.save(product);
        }
        order.setTotal(total);
        return orders.save(order);
    }

    @GetMapping
    public List<Order> all(@RequestParam(required=false) String email) {
        return email == null || email.isBlank() ? orders.findAll() : orders.findByEmailIgnoreCaseOrderByCreatedAtDesc(email);
    }

    @GetMapping("/{id}")
    public Order one(@PathVariable Long id) {
        return orders.findById(id).orElseThrow();
    }

    @PatchMapping("/{id}/status")
    public Order status(@PathVariable Long id, @RequestParam String value) {
        Order order = orders.findById(id).orElseThrow();
        order.setStatus(value.toUpperCase());
        return orders.save(order);
    }
}
