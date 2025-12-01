package com.example.midterm_sw.dto;

import com.example.midterm_sw.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Date createdAt;
    private OrderStatus status;
    private Long userId;
    private List<Long> productIds;
}
