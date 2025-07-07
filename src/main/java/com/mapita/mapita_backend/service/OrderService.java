package com.mapita.mapita_backend.service;

import com.mapita.mapita_backend.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto save(OrderDto dto);
    OrderDto update(Long id, OrderDto dto);
    OrderDto get(Long id);
    List<OrderDto> getAll();
    void delete(Long id);

    List<OrderDto> saveAll(List<OrderDto> orders);
    List<OrderDto> getUserOrders(Long userId);

    int approveCompanyOrdersToday(Long companyId);
}
