package com.mapita.mapita_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long orderId;
    private Integer orderCount;
    private BigDecimal orderWeight;
    private Timestamp startDate;
    private Timestamp endDate;
    private Long productId;
    private Long companyId;
    private Long userId;
}
