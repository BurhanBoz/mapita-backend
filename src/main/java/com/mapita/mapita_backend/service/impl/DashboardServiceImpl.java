package com.mapita.mapita_backend.service.impl;

import com.mapita.mapita_backend.dto.DashboardDto;
import com.mapita.mapita_backend.repository.CompanyRepository;
import com.mapita.mapita_backend.repository.OrderRepository;
import com.mapita.mapita_backend.repository.UserRepository;
import com.mapita.mapita_backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public DashboardDto getDashboardStats() {
        long totalCompanies = companyRepository.count();
        long totalUsers = userRepository.count();
        long todayOrders = orderRepository.countByEndDate(LocalDate.now());
        long approvedOrders = orderRepository.countByStatus("READY");

        return new DashboardDto(totalCompanies, totalUsers, todayOrders, approvedOrders);
    }
}
