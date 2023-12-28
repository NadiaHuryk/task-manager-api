package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.dto.request.UserRegistrationRequestDto;
import com.example.taskmanagerapi.dto.request.UserRequestDto;
import com.example.taskmanagerapi.dto.response.UserResponseDto;
import com.example.taskmanagerapi.exeption.UsernameNotFoundException;
import com.example.taskmanagerapi.model.User;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws UsernameNotFoundException;

    User getById(Long id);

    User getByEmail(String email);

    UserResponseDto getCurrentUserInfo(Authentication authentication);

    UserResponseDto update(UserRequestDto user, Authentication authentication);

    void delete(Authentication authentication);

    void uploadProfilePicture(Authentication authentication,

                              MultipartFile image) throws IOException;

    byte[] getProfilePicture(Authentication authentication);
}
