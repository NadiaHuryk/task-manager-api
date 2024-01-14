package com.example.taskmanagerapi.exeption;

public class TaskException extends RuntimeException {
    public TaskException(String message) {
        super(message);
    }
}
