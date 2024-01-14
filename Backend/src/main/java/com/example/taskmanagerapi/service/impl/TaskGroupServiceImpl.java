package com.example.taskmanagerapi.service.impl;

import com.example.taskmanagerapi.dto.mapper.TaskGroupMapper;
import com.example.taskmanagerapi.dto.request.TaskGroupRequestDto;
import com.example.taskmanagerapi.dto.response.TaskGroupResponseDto;
import com.example.taskmanagerapi.exeption.TaskGroupException;
import com.example.taskmanagerapi.model.TaskGroup;
import com.example.taskmanagerapi.repository.TaskGroupRepository;
import com.example.taskmanagerapi.service.TaskGroupService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskGroupServiceImpl implements TaskGroupService {
    private TaskGroupRepository taskGroupRepository;
    private final TaskGroupMapper mapper;

    @Override
    public TaskGroupResponseDto save(TaskGroupRequestDto taskGroupDto) {
        TaskGroup taskGroup = taskGroupRepository.save(mapper.toModel(taskGroupDto));
        return mapper.toDto(taskGroup);
    }

    @Override
    public List<TaskGroupResponseDto> findAll() {
        return taskGroupRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        taskGroupRepository.deleteById(id);
    }

    @Override
    public TaskGroupResponseDto get(Long id) {
        return mapper.toDto(taskGroupRepository.findById(id).orElseThrow(()
                -> new TaskGroupException("Can't find group")));
    }
}
