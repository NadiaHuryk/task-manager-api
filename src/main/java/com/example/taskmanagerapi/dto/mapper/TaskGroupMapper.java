package com.example.taskmanagerapi.dto.mapper;

import com.example.taskmanagerapi.config.MapperConfig;
import com.example.taskmanagerapi.dto.request.TaskGroupRequestDto;
import com.example.taskmanagerapi.dto.response.TaskGroupResponseDto;
import com.example.taskmanagerapi.model.TaskGroup;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface TaskGroupMapper {
    TaskGroupResponseDto toDto(TaskGroup taskGroup);

    TaskGroup toModel(TaskGroupRequestDto requestDto);
}
