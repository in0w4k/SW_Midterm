package com.example.midterm_sw.mapper;

import com.example.midterm_sw.dto.OrderDto;
import com.example.midterm_sw.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order order);
    Order toEntity(OrderDto dto);
}
