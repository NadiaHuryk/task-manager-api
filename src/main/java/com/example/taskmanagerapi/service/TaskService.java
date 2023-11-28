package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.model.Task;
import java.util.List;

public interface TaskService {
    Task save(Task tast);

    Task get(Long id);

    Task update(Task player);

    void delete(Long id);

    List<Task> findAll(String email);
}
