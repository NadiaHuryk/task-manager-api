package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.dto.request.TaskRequestDto;
import com.example.taskmanagerapi.dto.response.TaskResponseDto;
import java.util.List;
import org.springframework.security.core.Authentication;

public interface TaskService {
    TaskResponseDto save(TaskRequestDto task);

    TaskResponseDto get(Long id);

    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto,
                               Authentication authentication);

    void delete(Long id, Authentication authentication);

    List<TaskResponseDto> findAll(Authentication authentication);
}
