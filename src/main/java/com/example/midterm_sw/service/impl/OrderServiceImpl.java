package com.example.midterm_sw.service.impl;

import com.example.midterm_sw.dto.OrderDto;
import com.example.midterm_sw.dto.ProductDto;
import com.example.midterm_sw.mapper.OrderMapper;
import com.example.midterm_sw.model.OrderStatus;
import com.example.midterm_sw.model.Order;
import com.example.midterm_sw.model.Product;
import com.example.midterm_sw.model.User;
import com.example.midterm_sw.repository.OrderRepository;
import com.example.midterm_sw.repository.ProductRepository;
import com.example.midterm_sw.repository.UserRepository;
import com.example.midterm_sw.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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
    public OrderDto addOrder(OrderDto dto) {
        Order order = orderMapper.toEntity(dto);
        if (order.getStatus() == null) order.setStatus(OrderStatus.CREATED);
        if (order.getCreatedAt() == null) order.setCreatedAt(new Date());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            order.setUser(user);
        }

        if (dto.getProductIds() != null) {
            List<Product> products = productRepository.findAllById(dto.getProductIds());
            order.setProducts(products);
        }

        orderRepository.save(order);

        return orderMapper.toDto(order);
    }

    @Override
    public void addProductToOrder(Long id, ProductDto dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Product product = productRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        order.getProducts().add(product);
        orderRepository.save(order);
    }

    @Override
    public void updateOrder(Long id, OrderDto dto) {
        Order updatedOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (dto.getCreatedAt() != null) updatedOrder.setCreatedAt(dto.getCreatedAt());
        if (dto.getStatus() != null) updatedOrder.setStatus(dto.getStatus());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            updatedOrder.setUser(user);
        }

        if (dto.getProductIds() != null) {
            List<Product> products = productRepository.findAllById(dto.getProductIds());
            updatedOrder.setProducts(products);
        }

        orderRepository.save(updatedOrder);
    }

    @Override
    public boolean deleteOrder(Long id) {
        Order deletedOrder = orderRepository.findById(id)
                .orElseThrow();
        orderRepository.delete(deletedOrder);
        return true;
    }
}