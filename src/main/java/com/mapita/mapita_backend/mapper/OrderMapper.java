package com.mapita.mapita_backend.mapper;

import com.mapita.mapita_backend.dto.OrderDto;
import com.mapita.mapita_backend.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class, ProductMapper.class, UserMapper.class})
public interface OrderMapper {

    OrderDto toDto(OrderEntity entity);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "user", ignore = true)
    OrderEntity toEntity(OrderDto dto);
}
