package com.mapita.mapita_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDto {
    private Long totalCompanies;
    private Long totalUsers;
    private Long todayOrders;
    private Long approvedOrders;
}
