package com.mapita.mapita_backend.mapper;

import com.mapita.mapita_backend.dto.CompanyDto;
import com.mapita.mapita_backend.dto.UserDto;
import com.mapita.mapita_backend.entity.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDto toDto(CompanyEntity entity);
    CompanyEntity toEntity(CompanyDto dto);
}
