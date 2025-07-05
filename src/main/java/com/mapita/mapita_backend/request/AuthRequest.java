package com.mapita.mapita_backend.request;

import lombok.Data;

@Data
public class AuthRequest {

    private String userName;
    private String password;
}
