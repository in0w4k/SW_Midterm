package com.example.midterm_sw.service.impl;

import com.example.midterm_sw.dto.OrderDto;
import com.example.midterm_sw.dto.ProductDto;
import com.example.midterm_sw.mapper.OrderMapper;
import com.example.midterm_sw.mapper.ProductMapper;
import com.example.midterm_sw.model.OrderStatus;
import com.example.midterm_sw.model.Order;
import com.example.midterm_sw.model.Product;
import com.example.midterm_sw.repository.OrderRepository;
import com.example.midterm_sw.service.OrderService;
import com.example.midterm_sw.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id).orElse(null));
    }

    @Override
    public void addOrder(OrderDto dto) {
        Order order = orderMapper.toEntity(dto);
        if (order.getStatus() == null) order.setStatus(OrderStatus.CREATED);
        orderRepository.save(order);
    }

    @Override
    public void addProductToOrder(Long id, ProductDto dto) {
        Order order = orderMapper.toEntity(getOrderById(id));
        Product product = productMapper.toEntity(productService.getProductById(dto.getId()));

        order.getProducts().add(product);
        orderRepository.save(order);
    }

    @Override
    public void updateOrder(Long id, OrderDto dto) {
        Order updatedOrder = orderMapper.toEntity(getOrderById(id));
        if (dto.getCreatedAt() != null) updatedOrder.setCreatedAt(dto.getCreatedAt());
        if (dto.getStatus() != null) updatedOrder.setStatus(dto.getStatus());
        orderRepository.save(updatedOrder);
    }

    @Override
    public boolean deleteOrder(Long id) {
        Order deletedOrder = orderMapper.toEntity(getOrderById(id));
        if (deletedOrder == null) {
            return false;
        } else {
            orderRepository.delete(deletedOrder);
            return true;
        }
    }
}
