package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.dto.mapper.UserMapper;
import com.example.taskmanagerapi.dto.request.UserLoginRequestDto;
import com.example.taskmanagerapi.dto.request.UserRegistrationRequestDto;
import com.example.taskmanagerapi.dto.response.UserLoginResponseDto;
import com.example.taskmanagerapi.dto.response.UserResponseDto;
import com.example.taskmanagerapi.exeption.UsernameNotFoundException;
import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.security.AuthenticationServiceImpl;
import com.example.taskmanagerapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationServiceImpl authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/registration")
    @Operation(summary = "Data for registration", description = "This endpoint allows users"
            + " to register a new account")
    public UserResponseDto register(@Parameter(schema = @Schema(type = "String",
            defaultValue = "{\n"
                    + "    \"email\":\"alice.smith@gmail.com\", \n"
                    + "    \"name\":\"alice smith\", \n"
                    + "    \"password\":\"Alice12345%\", \n"
                    + "    \"repeatPassword\":\"Alice12345%\"\n"
                    + "}")) @RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws UsernameNotFoundException {
        return userService.register(requestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "User authentication",
            description = "Authenticates a user and returns an access token")
    public UserLoginResponseDto login(@Parameter(schema = @Schema(
            type = "String", defaultValue = "{\n"
            + "    \"email\":\"alice.smith@gmail.com\",\n"
            + "    \"password\":\"Alice12345%\"\n"
            + "}")) @RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user info",
            description = "Retrieve the information of the current user")
    public UserResponseDto getUserInfo(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        return userMapper.toDto(user);
    }
}
