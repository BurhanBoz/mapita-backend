package com.mapita.mapita_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long productId;
    private String productName;
}
