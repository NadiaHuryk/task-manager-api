package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.constants.Constants;
import com.example.taskmanagerapi.dto.request.TaskRequestDto;
import com.example.taskmanagerapi.dto.response.TaskResponseDto;
import com.example.taskmanagerapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    @Operation(summary = "Create a new task", description = "This endpoint allows users "
            + "to create a new task.")
    public ResponseEntity<TaskResponseDto> create(@Parameter(schema = @Schema(type = "String",
            defaultValue = Constants.DEFAULT_TASK_REQUEST))
                                      @RequestBody @Valid TaskRequestDto taskRequestDto) {
        TaskResponseDto createdTask = taskService.save(taskRequestDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by ID", description = "This endpoint allows users "
            + "to get details of a specific task by its ID.")
    public ResponseEntity<TaskResponseDto> get(@Parameter(description = "Task id",
            example = "1") @PathVariable Long id) {
        TaskResponseDto task = taskService.get(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a task", description = "This endpoint allows users "
            + "to update an existing task by ID.")
    public ResponseEntity<TaskResponseDto> update(@Parameter(schema = @Schema(type = "String",
            defaultValue = Constants.DEFAULT_UPDATE_TASK_REQUEST))
                                      @RequestBody @Valid TaskRequestDto taskRequestDto,
                                    @PathVariable Long id, Authentication authentication) {
        TaskResponseDto updatedTask = taskService.updateTask(id, taskRequestDto, authentication);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping ("/{id}")
    @Operation(summary = "Delete task by ID", description = "This endpoint allows users "
            + "to delete task by its ID.")
    public ResponseEntity<Void> delete(@Parameter(description = "Task id",
            example = "1") @PathVariable Long id, Authentication authentication) {
        taskService.delete(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all tasks", description = "List of all tasks")
    public ResponseEntity<List<TaskResponseDto>> getAll(Authentication authentication) {
        List<TaskResponseDto> tasks = taskService.findAll(authentication);
        return ResponseEntity.ok(tasks);
    }
}
