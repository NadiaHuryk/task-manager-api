package com.example.taskmanagerapi.service.impl;

import com.example.taskmanagerapi.dto.mapper.TaskMapper;
import com.example.taskmanagerapi.dto.request.TaskRequestDto;
import com.example.taskmanagerapi.dto.response.TaskResponseDto;
import com.example.taskmanagerapi.exeption.TaskException;
import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.repository.TaskRepository;
import com.example.taskmanagerapi.service.TaskService;
import com.example.taskmanagerapi.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskMapper mapper;

    @Override
    public TaskResponseDto save(TaskRequestDto taskRequestDto) {
        Task task = taskRepository.save(mapper.toModel(taskRequestDto));
        return mapper.toDto(task);
    }

    @Override
    public TaskResponseDto get(Long id) {
        return mapper.toDto(taskRepository.findById(id).orElseThrow(
                () -> new TaskException("Can't get task")));
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto,
                                      Authentication authentication) {
        User currentUser = userService.getByEmail(authentication.getName());
        Task currentTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException("Can't get task"));

        if (!currentTask.getUser().getId().equals(currentUser.getId())) {
            throw new TaskException("You do not have permission to update this task");
        }
        Task task = mapper.toModel(taskRequestDto);
        task.setId(id);
        Task updatedTask = taskRepository.save(task);
        return mapper.toDto(updatedTask);
    }

    @Override
    public void delete(Long id, Authentication authentication) {
        User currentUser = userService.getByEmail(authentication.getName());
        Task currentTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException("Can't get task"));

        if (!currentTask.getUser().getId().equals(currentUser.getId())) {
            throw new TaskException("You do not have permission to delete this task");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskResponseDto> findAll(Authentication authentication) {
        User currentUser = userService.getByEmail(authentication.getName());
        List<Task> tasks = taskRepository.findAllByUserId(currentUser.getId());
        return tasks.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
