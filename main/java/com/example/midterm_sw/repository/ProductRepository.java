package com.example.midterm_sw.repository;

import com.example.midterm_sw.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
