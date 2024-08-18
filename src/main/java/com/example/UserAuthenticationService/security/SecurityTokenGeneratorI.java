package com.example.UserAuthenticationService.security;

import com.example.UserAuthenticationService.domain.User;

public interface SecurityTokenGeneratorI {
    String createToken(User user);
}
