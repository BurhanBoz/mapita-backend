package com.mapita.mapita_backend.service.impl;

import com.mapita.mapita_backend.dto.OrderDto;
import com.mapita.mapita_backend.entity.OrderEntity;
import com.mapita.mapita_backend.mapper.OrderMapper;
import com.mapita.mapita_backend.repository.CompanyRepository;
import com.mapita.mapita_backend.repository.OrderRepository;
import com.mapita.mapita_backend.repository.ProductRepository;
import com.mapita.mapita_backend.repository.UserRepository;
import com.mapita.mapita_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDto save(OrderDto dto) {
        OrderEntity entity = orderMapper.toEntity(dto);
        entity.setProduct(productRepository.findById(dto.getProductId()).orElseThrow());
        entity.setCompany(companyRepository.findById(dto.getCompanyId()).orElseThrow());
        entity.setUser(userRepository.findById(dto.getUserId()).orElseThrow());
        return orderMapper.toDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto update(Long id, OrderDto dto) {
        OrderEntity entity = orderRepository.findById(id).orElseThrow();
        entity.setOrderCount(dto.getOrderCount());
        entity.setOrderWeight(dto.getOrderWeight());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setProduct(productRepository.findById(dto.getProductId()).orElseThrow());
        entity.setCompany(companyRepository.findById(dto.getCompanyId()).orElseThrow());
        entity.setUser(userRepository.findById(dto.getUserId()).orElseThrow());
        return orderMapper.toDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto get(Long id) {
        return orderMapper.toDto(orderRepository.findById(id).orElseThrow());
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
