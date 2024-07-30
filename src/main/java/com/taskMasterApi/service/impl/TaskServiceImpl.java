package com.taskMasterApi.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskMasterApi.domain.enums.StatusEnum;
import com.taskMasterApi.domain.model.Task;
import com.taskMasterApi.exception.ResourceNotFoundException;
import com.taskMasterApi.repository.TaskRepository;
import com.taskMasterApi.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final AuditServiceImpl auditService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, AuditServiceImpl auditService) {
        this.taskRepository = taskRepository;
        this.auditService = auditService;
    }

    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        logger.info("Fetching all tasks");
        return taskRepository.findAll(pageable);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        logger.info("Fetching task by id: {}", id);
        return taskRepository.findById(id);
    }

    @Override
    public Optional<Task> getTaskByTitle(String title) {
        logger.info("Fetching task by title: {}", title);
        return taskRepository.findByTitle(title);
    }

    @Override
    @Transactional
    public Task saveTask(Task task, String username) {
        logger.info("Saving task: {}", task);
        auditService.logAction("Task", "CREATE METHOD", username);
        return taskRepository.save(task);
    }


    @Override
    @Transactional
    public Task updateTask(Long id, Task updatedTask, String username) {
        logger.info("Updating task with id: {}", id);
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatusEnum(updatedTask.getStatusEnum());
            auditService.logAction("Task", "UPDATE METHOD", username);
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    @Override
    @Transactional
    public Task updateTaskStatus(Long id, StatusEnum status, String username) {
        logger.info("Updating task status with id: {}", id);
        return taskRepository.findById(id).map(task -> {
            task.setStatusEnum(status);
            auditService.logAction("Task", "UPDATE STATUS METHOD", username);
            return taskRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteTask(Long id, String username) {
        logger.info("Deleting task with id: {}", id);
        taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        auditService.logAction("Task", "DELETE METHOD", username);
        taskRepository.deleteById(id);
    }
}