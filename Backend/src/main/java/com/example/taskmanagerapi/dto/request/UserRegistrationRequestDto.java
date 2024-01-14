package com.example.taskmanagerapi.dto.request;

import com.example.taskmanagerapi.validation.Email;
import com.example.taskmanagerapi.validation.FieldsValueMatch;
import com.example.taskmanagerapi.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRegistrationRequestDto {
    @NotBlank(message = "Name can't be blank")
    private String name;
    @Email
    @NotBlank(message = "Email can't be blank")
    private String email;
    @ValidPassword
    @NotBlank(message = "Password can't be blank")
    private String password;
    private String repeatPassword;
}
