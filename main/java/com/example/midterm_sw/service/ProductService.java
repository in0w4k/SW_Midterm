package com.example.midterm_sw.service;

import com.example.midterm_sw.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    void addProduct(ProductDto dto);
    void updateProduct(Long id, ProductDto dto);
    boolean deleteProduct(Long id);
}
