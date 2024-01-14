package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.constants.Constants;
import com.example.taskmanagerapi.dto.request.TaskGroupRequestDto;
import com.example.taskmanagerapi.dto.response.TaskGroupResponseDto;
import com.example.taskmanagerapi.service.TaskGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/task-groups")
public class TaskGroupController {
    private final TaskGroupService taskGroupService;

    @PostMapping
    @Operation(summary = "Create a new task group",
            description = "This endpoint allows users to create a new task group.")
    public ResponseEntity<TaskGroupResponseDto> create(@Parameter(schema = @Schema(type = "String",
            defaultValue = Constants.DEFAULT_TASK_GROUP_REQUEST)) @RequestBody
                                           @Valid TaskGroupRequestDto taskGroupRequestDto) {
        TaskGroupResponseDto createdGroup = taskGroupService.save(taskGroupRequestDto);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task group by ID", description = "This endpoint allows users "
            + "to get details of a specific task group by its ID.")
    public ResponseEntity<TaskGroupResponseDto> get(@Parameter(description = "Task group id",
            example = "1") @PathVariable Long id) {
        TaskGroupResponseDto taskGroup = taskGroupService.get(id);
        return ResponseEntity.ok(taskGroup);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task group by ID", description = "This endpoint allows users "
            + "to delete task group by its ID.")
    public ResponseEntity<Void> delete(@Parameter(description = "Task id",
            example = "1") @PathVariable Long id) {
        taskGroupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all task groups", description = "List of all task groups")
    public ResponseEntity<List<TaskGroupResponseDto>> getAll() {
        List<TaskGroupResponseDto> allGroups = taskGroupService.findAll();
        return ResponseEntity.ok(allGroups);
    }
}
