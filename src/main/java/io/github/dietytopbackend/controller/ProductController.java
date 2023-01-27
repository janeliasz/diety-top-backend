package io.github.dietytopbackend.controller;

import io.github.dietytopbackend.model.Product;
import io.github.dietytopbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getByNameContaining(@RequestParam("name") String name) {
        return productService.getByNameContaining(name);
    }
}
