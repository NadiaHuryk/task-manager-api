package com.example.taskmanagerapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskRequestDto {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Priority is required")
    private String priority;
    @NotBlank(message = "Status is required")
    private String status;
    @NotNull(message = "Due date is required")
    private LocalDate dueDate;
    @NotNull(message = "User ID is required")
    private Long userId;
    @NotNull(message = "Task group ID is required")
    private Long taskGroupId;
}
