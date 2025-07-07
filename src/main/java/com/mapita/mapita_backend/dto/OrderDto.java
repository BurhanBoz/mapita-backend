package com.mapita.mapita_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Long orderCount;
    private BigDecimal orderWeight;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProductDto product;
    private CompanyDto company;
    private UserDto user;
    private String status;
}
