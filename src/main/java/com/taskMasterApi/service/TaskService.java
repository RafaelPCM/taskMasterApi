package com.taskMasterApi.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taskMasterApi.domain.model.Task;

public interface TaskService {

    Page<Task> getAllTasks(Pageable pageable);

    Page<Task> getTasksByCompleted(Boolean completed, Pageable pageable);

    Task saveTask(Task task, String username);

    void deleteTask(Long id, String username);

    Task updateTask(Long id, Task updatedTask, String username);

    Optional<Task> getTaskById(Long id);

    Optional<Task> getTaskByTitle(String title);
}