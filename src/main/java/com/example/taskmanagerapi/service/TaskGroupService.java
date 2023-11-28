package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.model.TaskGroup;
import java.util.List;

public interface TaskGroupService {
    TaskGroup save(TaskGroup taskGroup);

    List<TaskGroup> findAll();

    void delete(Long id);

    TaskGroup get(Long id);
}
