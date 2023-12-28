package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.constants.Constants;
import com.example.taskmanagerapi.dto.request.UserLoginRequestDto;
import com.example.taskmanagerapi.dto.request.UserRegistrationRequestDto;
import com.example.taskmanagerapi.dto.request.UserRequestDto;
import com.example.taskmanagerapi.dto.response.UserLoginResponseDto;
import com.example.taskmanagerapi.dto.response.UserResponseDto;
import com.example.taskmanagerapi.exeption.UsernameNotFoundException;
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

    @PostMapping("/registration")
    @Operation(summary = "Data for registration", description = "This endpoint allows users"
            + " to register a new account")
    public ResponseEntity<UserResponseDto> register(@Parameter(schema = @Schema(type = "String",
            defaultValue = Constants.DEFAULT_USER_REGISTRATION_REQUEST))
                                        @RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws UsernameNotFoundException {
        UserResponseDto responseDto = userService.register(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "User authentication",
            description = "Authenticates a user and returns an access token")
    public ResponseEntity<UserLoginResponseDto> login(@Parameter(schema = @Schema(
            type = "String", defaultValue = Constants.DEFAULT_USER_LOGIN_REQUEST))
                                          @RequestBody @Valid UserLoginRequestDto requestDto) {
        UserLoginResponseDto loginResponseDto = authenticationService.authenticate(requestDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user info",
            description = "Retrieve the information of the current user")
    public ResponseEntity<UserResponseDto> getUserInfo(Authentication authentication) {
        UserResponseDto userInfo = userService.getCurrentUserInfo(authentication);
        return ResponseEntity.ok(userInfo);
    }

    @PatchMapping("/me")
    @Operation(summary = "Update user", description = "This endpoint allows update "
            + "user's information")
    public ResponseEntity<UserResponseDto> updateUser(@Parameter(schema = @Schema(
            type = "String", defaultValue = Constants.DEFAULT_USER_UPDATE_REQUEST))
                                          @RequestBody UserRequestDto requestDto,
                                      Authentication authentication) {
        UserResponseDto updatedUser = userService.update(requestDto, authentication);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping()
    @Operation(summary = "Delete user", description = "This endpoint allows delete user's account")
    public ResponseEntity<Void> deleteUser(Authentication authentication) {
        userService.delete(authentication);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/upload-profile-picture",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Upload picture", description = "This endpoint allows users to upload "
            + "their profile picture. The expected request part is a file with key 'image'.")
    public ResponseEntity<?> uploadProfilePicture(@RequestPart("image") MultipartFile image,
                                                       Authentication authentication) {
        try {
            userService.uploadProfilePicture(authentication, image);
            return ResponseEntity.ok("Profile picture updated successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not upload image: " + e.getMessage());
        }
    }

    @GetMapping("/me/profile-picture")
    @Operation(summary = "Get picture", description = "This endpoint allows users to retrieve "
            + "their profile picture.")
    public ResponseEntity<byte[]> getProfilePicture(Authentication authentication) {
        byte[] imageBytes = userService.getProfilePicture(authentication);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
}
