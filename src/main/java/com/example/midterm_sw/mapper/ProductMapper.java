package com.example.midterm_sw.mapper;

import com.example.midterm_sw.dto.ProductDto;
import com.example.midterm_sw.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
    @Mapping(target = "orders", ignore = true)
    Product toEntity(ProductDto dto);
}
