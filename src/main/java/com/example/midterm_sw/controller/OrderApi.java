package com.example.midterm_sw.controller;

import com.example.midterm_sw.dto.OrderDto;
import com.example.midterm_sw.dto.ProductDto;
import com.example.midterm_sw.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderApi {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable(name = "id") Long id) {
        OrderDto dto = orderService.getOrderById(id);
        if (dto == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderDto dto) {
        orderService.addOrder(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<?> addProductToOrder(@PathVariable(name = "id") Long id, @RequestBody ProductDto dto) {
        orderService.addProductToOrder(id, dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable(name = "id") Long id, @RequestBody OrderDto dto) {
        if (orderService.getOrderById(id) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        orderService.updateOrder(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "id") Long id) {
        if (orderService.deleteOrder(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
