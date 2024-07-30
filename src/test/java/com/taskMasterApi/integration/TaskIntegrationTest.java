package com.taskMasterApi.integration;

import com.taskMasterApi.domain.enums.StatusEnum;
import com.taskMasterApi.domain.model.Task;
import com.taskMasterApi.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TaskIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testCreateAndFindTask() {
        Task task = new Task();
        task.setTitle("Integration Test Task");
        task.setDescription("Integration Test Description");
        task.setStatusEnum(StatusEnum.TODO);
        taskRepository.save(task);

        Optional<Task> foundTask = taskRepository.findByTitle("Integration Test Task");
        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getTitle()).isEqualTo("Integration Test Task");
    }

    @Test
    public void testUpdateTaskStatus() {
        Task task = new Task();
        task.setTitle("Integration Test Task");
        task.setDescription("Integration Test Description");
        task.setStatusEnum(StatusEnum.TODO);
        taskRepository.save(task);

        Optional<Task> foundTask = taskRepository.findByTitle("Integration Test Task");
        foundTask.ifPresent(t -> {
            t.setStatusEnum(StatusEnum.COMPLETED);
            taskRepository.save(t);
        });

        Optional<Task> updatedTask = taskRepository.findByTitle("Integration Test Task");
        assertThat(updatedTask).isPresent();
        assertThat(updatedTask.get().getStatusEnum()).isEqualTo(StatusEnum.COMPLETED);
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task();
        task.setTitle("Integration Test Task");
        task.setDescription("Integration Test Description");
        task.setStatusEnum(StatusEnum.TODO);
        taskRepository.save(task);

        Optional<Task> foundTask = taskRepository.findByTitle("Integration Test Task");
        foundTask.ifPresent(t -> taskRepository.deleteById(t.getId()));

        Optional<Task> deletedTask = taskRepository.findByTitle("Integration Test Task");
        assertThat(deletedTask).isNotPresent();
    }

    @Test
    public void testFindAllTasksWithPagination() {
        // Create multiple tasks for pagination testing
        for (int i = 0; i < 15; i++) {
            Task task = new Task();
            task.setTitle("Task " + i);
            task.setDescription("Description " + i);
            task.setStatusEnum(StatusEnum.TODO);
            taskRepository.save(task);
        }

        // Test pagination
        Pageable pageable = PageRequest.of(0, 10);
        Page<Task> taskPage = taskRepository.findAll(pageable);
        assertThat(taskPage.getTotalElements()).isGreaterThanOrEqualTo(15);
        assertThat(taskPage.getContent().size()).isEqualTo(10);
    }

    @Test
    public void testFullUpdateTask() {
        Task task = new Task();
        task.setTitle("Original Task");
        task.setDescription("Original Description");
        task.setStatusEnum(StatusEnum.TODO);
        taskRepository.save(task);

        // Retrieve and update the task
        Optional<Task> foundTask = taskRepository.findByTitle("Original Task");
        foundTask.ifPresent(t -> {
            t.setTitle("Updated Task");
            t.setDescription("Updated Description");
            t.setStatusEnum(StatusEnum.IN_PROGRESS);
            taskRepository.save(t);
        });

        // Verify the update
        Optional<Task> updatedTask = taskRepository.findByTitle("Updated Task");
        assertThat(updatedTask).isPresent();
        assertThat(updatedTask.get().getTitle()).isEqualTo("Updated Task");
        assertThat(updatedTask.get().getDescription()).isEqualTo("Updated Description");
        assertThat(updatedTask.get().getStatusEnum()).isEqualTo(StatusEnum.IN_PROGRESS);
    }

    @Test
    public void testFindTaskById_NotFound() {
        Optional<Task> task = taskRepository.findById(999L);
        assertThat(task).isNotPresent();
    }

    @Test
    public void testFindTaskById() {
        Task task = new Task();
        task.setTitle("Find By ID Task");
        task.setDescription("Find By ID Description");
        task.setStatusEnum(StatusEnum.TODO);
        task = taskRepository.save(task);

        Optional<Task> foundTask = taskRepository.findById(task.getId());
        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getTitle()).isEqualTo("Find By ID Task");
    }
}
