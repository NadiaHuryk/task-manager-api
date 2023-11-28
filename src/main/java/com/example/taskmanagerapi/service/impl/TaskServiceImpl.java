package com.example.taskmanagerapi.service.impl;

import com.example.taskmanagerapi.exeption.TaskException;
import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.repository.TaskRepository;
import com.example.taskmanagerapi.service.TaskService;
import com.example.taskmanagerapi.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task get(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new TaskException("Can't get task"));
    }

    @Override
    public Task update(Task task) {
        if (taskRepository.existsById(task.getId())) {
            return taskRepository.save(task);
        }
        throw new TaskException("Can't find task");
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findAll(String email) {
        User user = userService.getByEmail(email);
        return taskRepository.findAllByUserId(user.getId());
    }
}
