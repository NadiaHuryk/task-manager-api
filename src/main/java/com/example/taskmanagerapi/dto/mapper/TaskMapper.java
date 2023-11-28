package com.example.taskmanagerapi.dto.mapper;

import com.example.taskmanagerapi.config.MapperConfig;
import com.example.taskmanagerapi.dto.request.TaskRequestDto;
import com.example.taskmanagerapi.dto.response.TaskResponseDto;
import com.example.taskmanagerapi.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface TaskMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "taskGroupId", source = "taskGroup.id")
    TaskResponseDto toDto(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "taskGroup.id", source = "taskGroupId")
    Task toModel(TaskRequestDto requestDto);
}
