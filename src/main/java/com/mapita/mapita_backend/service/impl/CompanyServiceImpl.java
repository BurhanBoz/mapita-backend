package com.mapita.mapita_backend.service.impl;

import com.mapita.mapita_backend.dto.CompanyDto;
import com.mapita.mapita_backend.entity.CompanyEntity;
import com.mapita.mapita_backend.mapper.CompanyMapper;
import com.mapita.mapita_backend.repository.CompanyRepository;
import com.mapita.mapita_backend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    @Override
    public CompanyDto save(CompanyDto dto) {
        CompanyEntity entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public CompanyDto get(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("company not found!"));
    }

    @Override
    public CompanyDto update(Long id, CompanyDto dto) {
        CompanyEntity existEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("company not found!"));
        existEntity.setCompanyName(dto.getCompanyName());
        return mapper.toDto(repository.save(existEntity));
    }

    @Override
    public List<CompanyDto> list() {
        return repository.findAll()
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
