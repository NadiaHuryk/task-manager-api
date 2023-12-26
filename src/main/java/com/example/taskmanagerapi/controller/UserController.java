package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.dto.mapper.UserMapper;
import com.example.taskmanagerapi.dto.request.UserLoginRequestDto;
import com.example.taskmanagerapi.dto.request.UserRegistrationRequestDto;
import com.example.taskmanagerapi.dto.request.UserRequestDto;
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
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PatchMapping("/me")
    public UserResponseDto updateUser(@RequestBody UserRequestDto requestDto,
                                      Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setPhone(requestDto.getPhone());
        user = userService.save(user);
        return userMapper.toDto(user);
    }

    @DeleteMapping()
    public void deleteUser(Authentication authentication) {
        User currentUser = userService.getByEmail(authentication.getName());
        userService.delete(currentUser.getId());
    }

    @PostMapping(value = "/upload-profile-picture",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadProfilePicture(@RequestPart("image") MultipartFile image,
                                                       Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        try {
            byte[] imageBytes = image.getBytes();
            user.setProfilePicture(imageBytes);
            userService.save(user);
            return ResponseEntity.ok("Profile picture updated successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not upload image: " + e.getMessage());
        }
    }

    @GetMapping("/me/profile-picture")
    public ResponseEntity<byte[]> getProfilePicture(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        byte[] imageBytes = user.getProfilePicture();

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
}
