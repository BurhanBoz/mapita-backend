package com.mapita.mapita_backend.mapper;

import com.mapita.mapita_backend.dto.UserDto;
import com.mapita.mapita_backend.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface UserMapper {

    UserDto toDto(UserEntity entity);
    UserEntity toEntity(UserDto dto);
}
