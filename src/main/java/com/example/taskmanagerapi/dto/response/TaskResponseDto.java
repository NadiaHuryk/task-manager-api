package com.example.taskmanagerapi.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;
    private String title;

    private String description;

    private String priority;

    private String status;

    private LocalDate dueDate;
    private Long userId;
    private Long taskGroupId;
}
