package com.example.taskmanagerapi.dto.mapper;

import com.example.taskmanagerapi.config.MapperConfig;
import com.example.taskmanagerapi.dto.response.UserResponseDto;
import com.example.taskmanagerapi.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);
}
