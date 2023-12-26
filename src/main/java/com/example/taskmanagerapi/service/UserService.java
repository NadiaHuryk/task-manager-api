package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.dto.request.UserRegistrationRequestDto;
import com.example.taskmanagerapi.dto.response.UserResponseDto;
import com.example.taskmanagerapi.exeption.UsernameNotFoundException;
import com.example.taskmanagerapi.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws UsernameNotFoundException;

    User getById(Long id);

    User getByEmail(String email);

    User save(User user);

    void delete(Long id);
}
