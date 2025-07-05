package com.mapita.mapita_backend.repository;

import com.mapita.mapita_backend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT COUNT(o) FROM OrderEntity o WHERE o.product.productId = :productId AND (o.endDate IS NULL OR o.endDate >= CURRENT_TIMESTAMP)")
    long countActiveOrFutureOrders(@Param("productId") Long productId);
}
