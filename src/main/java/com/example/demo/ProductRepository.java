package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    ArrayList<Product> findByNameContainingIgnoreCase(String name);
    ArrayList<Product> findByDescriptionContainingIgnoreCase(String details);
    Optional<Product> findById(Long id);

   /* @Override
    < Product> S save(S s);
    */

}
