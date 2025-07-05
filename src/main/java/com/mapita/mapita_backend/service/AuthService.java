package com.mapita.mapita_backend.service;

import com.mapita.mapita_backend.request.AuthRequest;

import java.util.Map;

public interface AuthService {

    Map<String, String> login(AuthRequest request);
}
