package com.mapita.mapita_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_count")
    private int productCount;
    @Column(name = "product_weight")
    private BigDecimal productWeight;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
}
