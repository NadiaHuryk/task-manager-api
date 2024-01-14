package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.dto.request.TaskGroupRequestDto;
import com.example.taskmanagerapi.dto.response.TaskGroupResponseDto;
import java.util.List;

public interface TaskGroupService {
    TaskGroupResponseDto save(TaskGroupRequestDto taskGroup);

    List<TaskGroupResponseDto> findAll();

    void delete(Long id);

    TaskGroupResponseDto get(Long id);
}
