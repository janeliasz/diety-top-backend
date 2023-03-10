package io.github.dietytopbackend.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productRepository.save(
            Product.builder()
                .id(1)
                .name("pomidor")
                .build()
        );
        this.productRepository.save(
            Product.builder()
                    .id(2)
                    .name("por")
                    .build()
        );
        this.productRepository.save(
            Product.builder()
                    .id(3)
                    .name("czarna porzeczka")
                    .build()
        );
    }

    public Product getById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return product.isEmpty() ? null : product.get();
    }

    public List<Product> getByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }
}
