package com.mapita.mapita_backend.service.impl;

import com.mapita.mapita_backend.dto.UserDto;
import com.mapita.mapita_backend.request.AuthRequest;
import com.mapita.mapita_backend.service.AuthService;
import com.mapita.mapita_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailServiceImpl userDetailService;

    @Override
    public Map<String, String> login(AuthRequest request) {
        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );
        UserDto userDto = userDetailService.loadUserByUserName(request.getUserName());
        String token = jwtUtil.generateToken(userDto.getUserName());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("username", userDto.getUserName());
        response.put("companyName", userDto.getCompany().getCompanyName());
        response.put("companyId", userDto.getCompany().getCompanyId().toString());
        response.put("userId", userDto.getUserId().toString());
        response.put("role", userDto.getRoleType());
        return response;
    }
}
