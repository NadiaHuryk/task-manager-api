package com.example.taskmanagerapi.service.impl;

import com.example.taskmanagerapi.dto.mapper.UserMapper;
import com.example.taskmanagerapi.dto.request.UserRegistrationRequestDto;
import com.example.taskmanagerapi.dto.request.UserRequestDto;
import com.example.taskmanagerapi.dto.response.UserResponseDto;
import com.example.taskmanagerapi.exeption.DataProcessingException;
import com.example.taskmanagerapi.exeption.UsernameNotFoundException;
import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.repository.UserRepository;
import com.example.taskmanagerapi.service.UserService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws UsernameNotFoundException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new UsernameNotFoundException("Can't register user");
        }
        User user = new User();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new DataProcessingException("Can't find user"));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new DataProcessingException("Can't find user"));
    }

    @Override
    public UserResponseDto getCurrentUserInfo(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new DataProcessingException("Can't find user"));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto update(UserRequestDto requestDto, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new DataProcessingException("Can't find user"));
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPhone(requestDto.getPhone());
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void delete(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new DataProcessingException("Can't find user"));
        userRepository.deleteById(user.getId());
    }

    @Override
    public void uploadProfilePicture(Authentication authentication,
                                     MultipartFile image) throws IOException {
        User currentUser = userRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new DataProcessingException("Can't find user"));
        byte[] imageBytes = image.getBytes();
        currentUser.setProfilePicture(imageBytes);
        userRepository.save(currentUser);
    }

    @Override
    public byte[] getProfilePicture(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new DataProcessingException("Can't find user"));
        return user.getProfilePicture();
    }
}
