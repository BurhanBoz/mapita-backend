package com.mapita.mapita_backend.service.impl;

import com.mapita.mapita_backend.dto.OrderDto;
import com.mapita.mapita_backend.entity.OrderEntity;
import com.mapita.mapita_backend.mapper.OrderMapper;
import com.mapita.mapita_backend.repository.CompanyRepository;
import com.mapita.mapita_backend.repository.OrderRepository;
import com.mapita.mapita_backend.repository.ProductRepository;
import com.mapita.mapita_backend.repository.UserRepository;
import com.mapita.mapita_backend.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
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
        entity.setProduct(productRepository.findById(dto.getProduct().getProductId()).orElseThrow());
        entity.setCompany(companyRepository.findById(dto.getCompany().getCompanyId()).orElseThrow());
        entity.setUser(userRepository.findById(dto.getUser().getUserId()).orElseThrow());
        return orderMapper.toDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto update(Long id, OrderDto dto) {
        OrderEntity entity = orderRepository.findById(id).orElseThrow();
        entity.setOrderCount(dto.getOrderCount());
        entity.setOrderWeight(dto.getOrderWeight());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setProduct(productRepository.findById(dto.getProduct().getProductId()).orElseThrow());
        entity.setCompany(companyRepository.findById(dto.getCompany().getCompanyId()).orElseThrow());
        entity.setUser(userRepository.findById(dto.getUser().getUserId()).orElseThrow());
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

    @Override
    public List<OrderDto> saveAll(List<OrderDto> orders) {
        List<OrderEntity> entities = orders.stream().map(dto -> {
            OrderEntity entity = orderMapper.toEntity(dto);

            // Resolve foreign key relationships
            entity.setProduct(
                    productRepository.findById(dto.getProduct().getProductId())
                            .orElseThrow(() -> new RuntimeException("Product not found: " + dto.getProduct().getProductId()))
            );

            entity.setCompany(
                    companyRepository.findById(dto.getCompany().getCompanyId())
                            .orElseThrow(() -> new RuntimeException("Company not found: " + dto.getCompany().getCompanyId()))
            );

            entity.setUser(
                    userRepository.findById(dto.getUser().getUserId())
                            .orElseThrow(() -> new RuntimeException("User not found: " + dto.getUser().getUserId()))
            );

            // Set default dates if not provided
            if (dto.getStartDate() == null) {
                entity.setStartDate(LocalDate.now());
            }

            if (dto.getEndDate() == null) {
                entity.setEndDate(LocalDate.now().plusDays(1));
            } else {
                entity.setEndDate(LocalDate.now().plusDays(1));
            }

            return entity;
        }).toList();

        List<OrderEntity> savedEntities = orderRepository.saveAll(entities);
        return savedEntities.stream().map(orderMapper::toDto).toList();
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        List<OrderEntity> userOrdersEntity = orderRepository.findActiveOrdersByUserId(userId);
        return userOrdersEntity.stream().map(orderMapper::toDto).toList();
    }

    @Override
    @Transactional
    public int approveCompanyOrdersToday(Long companyId) {
        return orderRepository.approveTodayOrdersByCompany(companyId);
    }
}
