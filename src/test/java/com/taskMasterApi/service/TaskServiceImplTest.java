package com.taskMasterApi.service;

import com.taskMasterApi.domain.enums.StatusEnum;
import com.taskMasterApi.domain.model.Task;
import com.taskMasterApi.exception.ResourceNotFoundException;
import com.taskMasterApi.repository.TaskRepository;
import com.taskMasterApi.service.impl.AuditServiceImpl;
import com.taskMasterApi.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AuditServiceImpl auditService;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatusEnum(StatusEnum.TODO);
    }

    @Test
    void testGetAllTasks() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Task> taskList = Arrays.asList(task);
        Page<Task> taskPage = new PageImpl<>(taskList);

        when(taskRepository.findAll(pageable)).thenReturn(taskPage);

        Page<Task> result = taskService.getAllTasks(pageable);

        assertEquals(1, result.getContent().size());
        assertEquals("Test Task", result.getContent().get(0).getTitle());
    }

    @Test
    void testSaveTask() {
        when(taskRepository.save(task)).thenReturn(task);

        Task savedTask = taskService.saveTask(task, "username");

        assertNotNull(savedTask);
        assertEquals("Test Task", savedTask.getTitle());
        verify(auditService, times(1)).logAction(anyString(), anyString(), anyString());
    }

    @Test
    void testUpdateTask() {
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        Task updatedTask = new Task();
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");

        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.updateTask(task.getId(), updatedTask, "username");

        assertNotNull(result);
        assertEquals("Updated Task", result.getTitle());
        assertEquals("Updated Description", result.getDescription());
        verify(auditService, times(1)).logAction(anyString(), anyString(), anyString());
    }

    @Test
    void testUpdateTaskStatus() {
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        Task updatedTask = new Task();
        updatedTask.setStatusEnum(StatusEnum.COMPLETED);

        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.updateTaskStatus(task.getId(), StatusEnum.COMPLETED, "username");

        assertNotNull(result);

        assertEquals(StatusEnum.COMPLETED, result.getStatusEnum());

        verify(auditService, times(1)).logAction(anyString(), anyString(), anyString());
    }

    @Test
    void testDeleteTask() {
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        taskService.deleteTask(task.getId(), "username");

        verify(taskRepository, times(1)).deleteById(task.getId());
        verify(auditService, times(1)).logAction(anyString(), anyString(), anyString());
    }

    @Test
    void testGetTaskById() {
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(task.getId());

        assertTrue(result.isPresent());
        assertEquals("Test Task", result.get().getTitle());
    }

    @Test
    void testGetTaskByTitle() {
        when(taskRepository.findByTitle(task.getTitle())).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskByTitle(task.getTitle());

        assertTrue(result.isPresent());
        assertEquals("Test Task", result.get().getTitle());
    }

    @Test
    void testUpdateTask_NotFound() {
        Task updatedTask = new Task();
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");

        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.updateTask(999L, updatedTask, "username");
        });
    }

    @Test
    void testUpdateTaskStatus_NotFound() {
        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.updateTaskStatus(999L, StatusEnum.COMPLETED, "username");
        });
    }

    @Test
    void testDeleteTask_NotFound() {
        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.deleteTask(999L, "username");
        });
    }

}
