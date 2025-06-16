package com.mapita.mapita_backend.controller;

import com.mapita.mapita_backend.dto.CompanyDto;
import com.mapita.mapita_backend.dto.ProductDto;
import com.mapita.mapita_backend.service.CompanyService;
import com.mapita.mapita_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyDto> create(@RequestBody CompanyDto company) {
        return ResponseEntity.ok(companyService.save(company));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> list() {
        return ResponseEntity.ok(companyService.list());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> update(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.update(id, companyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
