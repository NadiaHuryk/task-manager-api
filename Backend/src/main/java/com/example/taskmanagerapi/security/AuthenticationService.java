package com.example.taskmanagerapi.security;

import com.example.taskmanagerapi.dto.request.UserLoginRequestDto;
import com.example.taskmanagerapi.dto.response.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto requestDto);
}
