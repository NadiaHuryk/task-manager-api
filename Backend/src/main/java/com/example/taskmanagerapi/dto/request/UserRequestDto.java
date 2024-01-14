package com.example.taskmanagerapi.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String name;
    private String phone;
}
