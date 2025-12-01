package com.example.midterm_sw.service.impl;

import com.example.midterm_sw.dto.ProductDto;
import com.example.midterm_sw.mapper.ProductMapper;
import com.example.midterm_sw.model.Product;
import com.example.midterm_sw.repository.ProductRepository;
import com.example.midterm_sw.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElse(null));
    }

    @Override
    public ProductDto addProduct(ProductDto dto) {
        Product product = productMapper.toEntity(dto);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void updateProduct(Long id, ProductDto dto) {
        Product updatedProduct = productRepository.findById(id)
                .orElseThrow();
        if (dto.getName() != null) updatedProduct.setName(dto.getName());
        if (dto.getDescription() != null) updatedProduct.setDescription(dto.getDescription());
        if (dto.getCategory() != null) updatedProduct.setCategory(dto.getCategory());
        if (dto.getPrice() != null) updatedProduct.setPrice(dto.getPrice());
        if (dto.getStock() != null) updatedProduct.setStock(dto.getStock());

        productRepository.save(updatedProduct);
    }

    @Override
    public boolean deleteProduct(Long id) {
        Product deletedProduct = productRepository.findById(id)
                .orElseThrow();
        productRepository.delete(deletedProduct);
        return true;
    }
}
