package com.example.midterm_sw.mapper;

import com.example.midterm_sw.dto.ProductDto;
import com.example.midterm_sw.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void convertEntityToDtoTest(){
        Product entityProduct = new Product(1L, "sofa", "description", "furniture", 199.9, 120, null);

        ProductDto dtoProduct = productMapper.toDto(entityProduct);

        Assertions.assertNotNull(dtoProduct);

        Assertions.assertNotNull(dtoProduct.getId());
        Assertions.assertNotNull(dtoProduct.getName());
        Assertions.assertNotNull(dtoProduct.getDescription());
        Assertions.assertNotNull(dtoProduct.getCategory());
        Assertions.assertNotNull(dtoProduct.getPrice());
        Assertions.assertNotNull(dtoProduct.getStock());


        Assertions.assertEquals(entityProduct.getId(), dtoProduct.getId());
        Assertions.assertEquals(entityProduct.getName(), dtoProduct.getName());
        Assertions.assertEquals(entityProduct.getDescription(), dtoProduct.getDescription());
        Assertions.assertEquals(entityProduct.getCategory(), dtoProduct.getCategory());
        Assertions.assertEquals(entityProduct.getPrice(), dtoProduct.getPrice());
        Assertions.assertEquals(entityProduct.getStock(), dtoProduct.getStock());
    }

    @Test
    void convertDtoToEntityTest(){
        ProductDto dtoProduct = new ProductDto(1L, "sofa", "description", "furniture", 199.9, 120);

        Product entityProduct = productMapper.toEntity(dtoProduct);

        Assertions.assertNotNull(entityProduct);

        Assertions.assertNotNull(entityProduct.getId());
        Assertions.assertNotNull(entityProduct.getName());
        Assertions.assertNotNull(entityProduct.getDescription());
        Assertions.assertNotNull(entityProduct.getCategory());
        Assertions.assertNotNull(entityProduct.getPrice());
        Assertions.assertNotNull(entityProduct.getStock());

        Assertions.assertEquals(dtoProduct.getId(), entityProduct.getId());
        Assertions.assertEquals(dtoProduct.getName(), entityProduct.getName());
        Assertions.assertEquals(dtoProduct.getDescription(), entityProduct.getDescription());
        Assertions.assertEquals(dtoProduct.getCategory(), entityProduct.getCategory());
        Assertions.assertEquals(dtoProduct.getPrice(), entityProduct.getPrice());
        Assertions.assertEquals(dtoProduct.getStock(), entityProduct.getStock());
    }
}