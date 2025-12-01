package com.example.midterm_sw.mapper;

import com.example.midterm_sw.dto.OrderDto;
import com.example.midterm_sw.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void convertEntityToDtoTest(){
        User user = new User();
        user.setId(10L);

        Product p1 = new Product(1L, "phone", "description", "tech", 200.0, 5, null);
        Product p2 = new Product(2L, "laptop", "description", "tech", 300.0, 2, null);
        List<Product> products = List.of(p1, p2);

        Order entityOrder = new Order(1L, new Date(), OrderStatus.CREATED, user, products);

        OrderDto dtoOrder = orderMapper.toDto(entityOrder);

        Assertions.assertNotNull(dtoOrder);

        Assertions.assertNotNull(dtoOrder.getId());
        Assertions.assertNotNull(dtoOrder.getCreatedAt());
        Assertions.assertNotNull(dtoOrder.getStatus());
        Assertions.assertNotNull(dtoOrder.getUserId());
        Assertions.assertNotNull(dtoOrder.getProductIds());

        Assertions.assertEquals(entityOrder.getId(), dtoOrder.getId());
        Assertions.assertEquals(entityOrder.getCreatedAt(), dtoOrder.getCreatedAt());
        Assertions.assertEquals(entityOrder.getStatus(), dtoOrder.getStatus());
        Assertions.assertEquals(entityOrder.getUser().getId(), dtoOrder.getUserId());
        Assertions.assertEquals(entityOrder.getProducts().stream().map(Product::getId).toList(), dtoOrder.getProductIds());
    }

    @Test
    void convertDtoToEntityTest() {
        OrderDto dtoOrder = new OrderDto(1L, new Date(), OrderStatus.CREATED, 5L, List.of(1L, 2L, 3L));

        Order entityOrder = orderMapper.toEntity(dtoOrder);

        Assertions.assertNotNull(entityOrder);

        Assertions.assertNotNull(entityOrder.getId());
        Assertions.assertNotNull(entityOrder.getCreatedAt());
        Assertions.assertNotNull(entityOrder.getStatus());
        Assertions.assertNotNull(entityOrder.getUser());

        Assertions.assertNull(entityOrder.getProducts());

        Assertions.assertEquals(dtoOrder.getId(), entityOrder.getId());
        Assertions.assertEquals(dtoOrder.getCreatedAt(), entityOrder.getCreatedAt());
        Assertions.assertEquals(dtoOrder.getStatus(), entityOrder.getStatus());
        Assertions.assertEquals(dtoOrder.getUserId(), entityOrder.getUser().getId());
    }
}