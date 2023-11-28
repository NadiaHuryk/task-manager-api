package com.example.taskmanagerapi.service.impl;

import com.example.taskmanagerapi.exeption.TaskGroupException;
import com.example.taskmanagerapi.model.TaskGroup;
import com.example.taskmanagerapi.repository.TaskGroupRepository;
import com.example.taskmanagerapi.service.TaskGroupService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskGroupServiceImpl implements TaskGroupService {
    private TaskGroupRepository taskGroupRepository;

    @Override
    public TaskGroup save(TaskGroup taskGroup) {
        return taskGroupRepository.save(taskGroup);
    }

    @Override
    public List<TaskGroup> findAll() {
        return taskGroupRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        taskGroupRepository.deleteById(id);
    }

    @Override
    public TaskGroup get(Long id) {
        return taskGroupRepository.findById(id).orElseThrow(()
                -> new TaskGroupException("Can't find group"));
    }
}
