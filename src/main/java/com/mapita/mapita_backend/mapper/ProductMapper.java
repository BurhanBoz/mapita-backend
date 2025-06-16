package com.mapita.mapita_backend.mapper;

import com.mapita.mapita_backend.dto.ProductDto;
import com.mapita.mapita_backend.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(ProductEntity entity);
    ProductEntity toEntity(ProductDto dto);
}
