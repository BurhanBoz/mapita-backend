package com.mapita.mapita_backend.service;

import com.mapita.mapita_backend.dto.CompanyDto;
import com.mapita.mapita_backend.dto.ProductDto;

import java.util.List;

public interface CompanyService {

    CompanyDto save(CompanyDto dto);
    CompanyDto get(Long id);
    CompanyDto update(Long id, CompanyDto dto);
    List<CompanyDto> list();
    void delete(Long id);
}
