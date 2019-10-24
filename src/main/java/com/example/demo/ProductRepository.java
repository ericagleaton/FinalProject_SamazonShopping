package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByNameContainingIgnoreCase(String name);
    Product findByDescriptionContainingIgnoreCase(String details);
}
