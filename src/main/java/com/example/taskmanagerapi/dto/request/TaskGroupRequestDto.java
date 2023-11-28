package com.example.taskmanagerapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskGroupRequestDto {
    @NotBlank(message = "Name is required")
    private String name;
}
