package com.mapita.mapita_backend.repository;

import com.mapita.mapita_backend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT COUNT(o) FROM OrderEntity o WHERE o.product.productId = :productId AND (o.endDate IS NULL OR o.endDate >= CURRENT_TIMESTAMP)")
    long countActiveOrFutureOrders(@Param("productId") Long productId);

    @Query("SELECT o FROM OrderEntity o WHERE o.user.userId = :userId AND o.endDate > CURRENT_TIMESTAMP")
    List<OrderEntity> findActiveOrdersByUserId(Long userId);

    @Modifying
    @Query("UPDATE OrderEntity o SET o.status = 'READY' " +
            "WHERE o.company.companyId = :companyId ")
    Integer approveTodayOrdersByCompany(@Param("companyId") Long companyId);

    Long countByEndDate(LocalDate date);
    Long countByStatus(String status);
}
