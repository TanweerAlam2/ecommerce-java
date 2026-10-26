package com.example.marketly.product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/admin/products")
public class ProductApiController {
 private final ProductRepository repo;
 public ProductApiController(ProductRepository repo){this.repo=repo;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Product create(@Valid @RequestBody Product p){p.setId(null); return repo.save(p);}
 @PutMapping("/{id}") public Product update(@PathVariable Long id,@Valid @RequestBody Product p){
  Product old=repo.findById(id).orElseThrow(); old.setName(p.getName()); old.setCategory(p.getCategory()); old.setPrice(p.getPrice()); old.setOriginalPrice(p.getOriginalPrice()); old.setImageUrl(p.getImageUrl()); old.setDescription(p.getDescription()); old.setRating(p.getRating()); old.setStock(p.getStock()); return repo.save(old);
 }
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){repo.deleteById(id);}
 @GetMapping("/categories") public List<String> categories(){return repo.findAll().stream().map(Product::getCategory).distinct().sorted().toList();}
}
