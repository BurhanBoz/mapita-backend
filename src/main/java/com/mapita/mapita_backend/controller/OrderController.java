package com.mapita.mapita_backend.controller;

import com.mapita.mapita_backend.dto.OrderDto;
import com.mapita.mapita_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
//@CrossOrigin(origins = {"${frontend.domain}"}, maxAge = 3600)
public class OrderController {


    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto dto) {
        return ResponseEntity.ok(orderService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(@PathVariable Long id, @RequestBody OrderDto dto) {
        return ResponseEntity.ok(orderService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/bulk-save")
    public ResponseEntity<List<OrderDto>> saveAll(@RequestBody List<OrderDto> orders) {
        return ResponseEntity.ok(orderService.saveAll(orders));
    }

    @GetMapping(path = "/user-orders/{id}")
    public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getUserOrders(id));
    }

    @PutMapping("/approve/{companyId}")
    public ResponseEntity<Integer> approveTodayOrders(@PathVariable Long companyId) {
        return ResponseEntity.ok(orderService.approveCompanyOrdersToday(companyId));
    }
}
