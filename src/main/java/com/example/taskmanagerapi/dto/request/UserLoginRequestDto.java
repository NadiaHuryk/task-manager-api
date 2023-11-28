package com.example.taskmanagerapi.dto.request;

import com.example.taskmanagerapi.validation.Email;
import com.example.taskmanagerapi.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @Email
    @NotBlank(message = "Email can't be blank")
    private String email;
    @ValidPassword
    @NotBlank(message = "Password can't be blank")
    private String password;
}
