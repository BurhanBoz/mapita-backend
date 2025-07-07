package com.mapita.mapita_backend.controller;

import com.mapita.mapita_backend.dto.DashboardDto;
import com.mapita.mapita_backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardDto getDashboardData() {
        return dashboardService.getDashboardStats();
    }
}
