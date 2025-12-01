package com.example.midterm_sw.service;

import com.example.midterm_sw.dto.OrderDto;
import com.example.midterm_sw.dto.ProductDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
    OrderDto addOrder(OrderDto dto);
    void addProductToOrder(Long id, ProductDto dto);
    void updateOrder(Long id, OrderDto dto);
    boolean deleteOrder(Long id);
}
