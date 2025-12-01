package com.example.midterm_sw.mapper;

import com.example.midterm_sw.dto.OrderDto;
import com.example.midterm_sw.model.Order;
import com.example.midterm_sw.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "products", target = "productIds", qualifiedByName = "mapProductsToIds")
    OrderDto toDto(Order order);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "products", ignore = true)
    Order toEntity(OrderDto dto);

    @Named("mapProductsToIds")
    static List<Long> mapProductsToIds(List<Product> products) {
        if (products == null) return null;
        return products.stream()
                .map(Product::getId)
                .toList();
    }
}
