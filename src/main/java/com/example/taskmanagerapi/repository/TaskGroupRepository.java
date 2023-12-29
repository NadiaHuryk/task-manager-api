package com.example.taskmanagerapi.repository;

import com.example.taskmanagerapi.model.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {
}
