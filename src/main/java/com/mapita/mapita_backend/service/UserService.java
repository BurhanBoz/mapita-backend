package com.mapita.mapita_backend.service;

import com.mapita.mapita_backend.dto.UserDto;

import java.util.List;

public interface UserService {


    UserDto save(UserDto dto);
    UserDto get(Long id);
    UserDto update(Long id, UserDto dto);
    List<UserDto> list();
    void delete(Long id);
}
