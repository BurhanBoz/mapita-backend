package com.mapita.mapita_backend.service;

import com.mapita.mapita_backend.dto.ProductDto;
import com.mapita.mapita_backend.dto.UserDto;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto dto);
    ProductDto get(Long id);
    ProductDto update(Long id, ProductDto dto);
    List<ProductDto> list();
    void delete(Long id);
}
