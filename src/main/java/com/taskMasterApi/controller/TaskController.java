package com.taskMasterApi.controller;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taskMasterApi.domain.enums.StatusEnum;
import com.taskMasterApi.domain.model.Task;
import com.taskMasterApi.exception.ResourceNotFoundException;
import com.taskMasterApi.service.TaskService;


@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Page<Task> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Boolean completed) {
        logger.info("Getting all tasks with pagination, page: {}, size: {}, completed: {}", page, size, completed);
        Pageable pageable = PageRequest.of(page, size);
        return taskService.getAllTasks(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        logger.info("Getting task by id: {}", id);
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                   .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Task> getTaskByTitle(@PathVariable String title) {
        logger.info("Getting task by title: {}", title);
        Optional<Task> task = taskService.getTaskByTitle(title);
        return task.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with title: " + title));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        logger.info("Creating new task: {}", task);
        Task createdTask = taskService.saveTask(task, "username");

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task, String username) {
        logger.info("Updating task with id: {}", id);
        Task updatedTask = taskService.updateTask(id, task, username);
        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/{id}/todo")
    public ResponseEntity<Task> setTaskToDo(@PathVariable Long id) {
        return updateTaskStatus(id, StatusEnum.TODO);
    }

    @PatchMapping("/{id}/in-progress")
    public ResponseEntity<Task> setTaskInProgress(@PathVariable Long id) {
        return updateTaskStatus(id, StatusEnum.IN_PROGRESS);
    }

    @PatchMapping("/{id}/completed")
    public ResponseEntity<Task> setTaskCompleted(@PathVariable Long id) {
        return updateTaskStatus(id, StatusEnum.COMPLETED);
    }

    @PatchMapping("/{id}/paused")
    public ResponseEntity<Task> setTaskPaused(@PathVariable Long id) {
        return updateTaskStatus(id, StatusEnum.PAUSED);
    }

    private ResponseEntity<Task> updateTaskStatus(Long id, StatusEnum status) {
        logger.info("Updating task status with id: {} to {}", id, status);
        Task updatedTask = taskService.updateTaskStatus(id, status, "username");
        return ResponseEntity.ok(updatedTask);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        logger.info("Deleting task with id: {}", id);
        taskService.deleteTask(id, "username");
        return ResponseEntity.noContent().build();
    }
}