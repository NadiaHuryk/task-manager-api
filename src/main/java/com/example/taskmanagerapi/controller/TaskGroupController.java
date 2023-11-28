package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.dto.mapper.TaskGroupMapper;
import com.example.taskmanagerapi.dto.request.TaskGroupRequestDto;
import com.example.taskmanagerapi.dto.response.TaskGroupResponseDto;
import com.example.taskmanagerapi.model.TaskGroup;
import com.example.taskmanagerapi.service.TaskGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
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
    private final TaskGroupMapper mapper;

    @PostMapping
    @Operation(summary = "Create a new task group",
            description = "This endpoint allows users to create a new task group.")
    public TaskGroupResponseDto create(@Parameter(schema = @Schema(type = "String",
            defaultValue = "{\n"
                    + "    \"name\": \"Example Task Group Title\" "
                    + "}")) @RequestBody @Valid TaskGroupRequestDto taskGroupRequestDto) {
        TaskGroup taskGroup = taskGroupService.save(mapper.toModel(taskGroupRequestDto));
        return mapper.toDto(taskGroup);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task group by ID", description = "This endpoint allows users "
            + "to get details of a specific task group by its ID.")
    public TaskGroupResponseDto get(@Parameter(description = "Task group id",
            example = "1") @PathVariable Long id) {
        return mapper.toDto(taskGroupService.get(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task group by ID", description = "This endpoint allows users "
            + "to delete task group by its ID.")
    public void delete(@Parameter(description = "Task id",
            example = "1") @PathVariable Long id) {
        taskGroupService.delete(id);
    }

    @GetMapping
    @Operation(summary = "Get all task groups", description = "List of all task groups")
    public List<TaskGroupResponseDto> getAll() {
        return taskGroupService.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
