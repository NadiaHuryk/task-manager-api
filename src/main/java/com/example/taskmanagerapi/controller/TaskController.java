package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.dto.mapper.TaskMapper;
import com.example.taskmanagerapi.dto.request.TaskRequestDto;
import com.example.taskmanagerapi.dto.response.TaskResponseDto;
import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper mapper;

    @PostMapping
    @Operation(summary = "Create a new task", description = "This endpoint allows users "
            + "to create a new task.")
    public TaskResponseDto create(@Parameter(schema = @Schema(type = "String",
            defaultValue = "{\n"
                    + "    \"title\": \"Example Task Title\",\n"
                    + "    \"description\": \"Example Task Description\",\n"
                    + "    \"priority\": \"HIGH\",\n"
                    + "    \"status\": \"NOT_STARTED\",\n"
                    + "    \"dueDate\": \"2023-11-11\",\n"
                    + "    \"userId\": 1,\n"
                    + "    \"taskGroupId\": 1\n"
                    + "}")) @RequestBody @Valid TaskRequestDto taskRequestDto) {
        Task task = taskService.save(mapper.toModel(taskRequestDto));
        return mapper.toDto(task);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by ID", description = "This endpoint allows users "
            + "to get details of a specific task by its ID.")
    public TaskResponseDto get(@Parameter(description = "Task id",
            example = "1") @PathVariable Long id) {
        return mapper.toDto(taskService.get(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a task", description = "This endpoint allows users "
            + "to update an existing task by ID.")
    public TaskResponseDto update(@Parameter(schema = @Schema(type = "String",
            defaultValue = "{\n"
                    + "    \"title\": \"Updated Task Title\",\n"
                    + "    \"description\": \"Updated Task Description\",\n"
                    + "    \"priority\": \"MEDIUM\",\n"
                    + "    \"status\": \"IN_PROGRESS\",\n"
                    + "    \"dueDate\": \"2023-12-31\",\n"
                    + "    \"userId\": 1,\n"
                    + "    \"taskGroupId\": 1\n"
                    + "}")) @RequestBody @Valid TaskRequestDto taskRequestDto,
                                    @PathVariable Long id, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Task currentTask = taskService.get(id);

        if (!currentTask.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You do not have permission to update this task");
        }
        Task task = mapper.toModel(taskRequestDto);
        task.setId(id);
        Task updatedTask = taskService.update(task);
        return mapper.toDto(updatedTask);
    }

    @DeleteMapping ("/{id}")
    @Operation(summary = "Delete task by ID", description = "This endpoint allows users "
            + "to delete task by its ID.")
    public void delete(@Parameter(description = "Task id",
            example = "1") @PathVariable Long id, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Task currentTask = taskService.get(id);

        if (!currentTask.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You do not have permission to delete this task");
        }
        taskService.delete(id);
    }

    @GetMapping
    @Operation(summary = "Get all tasks", description = "List of all tasks")
    public List<TaskResponseDto> getAll(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return taskService.findAll(currentUser.getEmail())
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
