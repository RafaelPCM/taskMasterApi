package com.taskMasterApi.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taskMasterApi.domain.enums.StatusEnum;
import com.taskMasterApi.domain.model.Task;

public interface TaskService {

    Page<Task> getAllTasks(Pageable pageable);

    Optional<Task> getTaskById(Long id);

    Optional<Task> getTaskByTitle(String title);

    Task saveTask(Task task, String username);

    Task updateTask(Long id, Task updatedTask, String username);

    Task updateTaskStatus(Long id, StatusEnum status, String username);

    void deleteTask(Long id, String username);
}