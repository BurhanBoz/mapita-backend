package com.mapita.mapita_backend.service.impl;

import com.mapita.mapita_backend.dto.UserDto;
import com.mapita.mapita_backend.entity.UserEntity;
import com.mapita.mapita_backend.mapper.UserMapper;
import com.mapita.mapita_backend.repository.CompanyRepository;
import com.mapita.mapita_backend.repository.UserRepository;
import com.mapita.mapita_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto save(UserDto dto) {
        dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        UserEntity user = userMapper.toEntity(dto);
        user.setCompany(companyRepository.findById(dto.getCompany().getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found")));

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto get(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserDto> list() {
        return userRepository.findAll()
                .stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setRoleType(dto.getRoleType());
        user.setCompany(companyRepository.findById(dto.getCompany().getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found")));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
