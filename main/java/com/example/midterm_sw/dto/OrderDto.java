package com.example.midterm_sw.dto;

import com.example.midterm_sw.model.OrderStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Date createdAt;
    private OrderStatus status;
    private Long userId;
    private List<Long> productIds;
}
