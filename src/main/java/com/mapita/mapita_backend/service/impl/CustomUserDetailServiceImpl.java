package com.mapita.mapita_backend.service.impl;

import com.mapita.mapita_backend.dto.UserDto;
import com.mapita.mapita_backend.entity.UserEntity;
import com.mapita.mapita_backend.mapper.UserMapper;
import com.mapita.mapita_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username);
        return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }

    public UserDto loadUserByUserName(String userName) {
        return userMapper.toDto(userRepository.findByUserName(userName));
    }
}
