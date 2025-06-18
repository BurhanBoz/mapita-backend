package com.mapita.mapita_backend.service.impl;

import com.mapita.mapita_backend.dto.ProductDto;
import com.mapita.mapita_backend.entity.ProductEntity;
import com.mapita.mapita_backend.entity.UserEntity;
import com.mapita.mapita_backend.mapper.ProductMapper;
import com.mapita.mapita_backend.repository.CompanyRepository;
import com.mapita.mapita_backend.repository.ProductRepository;
import com.mapita.mapita_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CompanyRepository companyRepository;
    private final ProductMapper mapper;
    @Override
    public ProductDto save(ProductDto dto) {
        ProductEntity entity = mapper.toEntity(dto);
        entity.setCompany(companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("company not found!")));
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public ProductDto get(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("company not found!"));
    }

    @Override
    public ProductDto update(Long id, ProductDto dto) {
        ProductEntity existProduct = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existProduct.setProductName(dto.getProductName());
        existProduct.setCompany(companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found")));
        return mapper.toDto(repository.save(existProduct));
    }

    @Override
    public List<ProductDto> list() {
        return repository.findAll()
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
