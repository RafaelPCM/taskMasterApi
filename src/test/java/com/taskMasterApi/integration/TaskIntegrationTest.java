package com.taskMasterApi.integration;

import com.taskMasterApi.domain.enums.StatusEnum;
import com.taskMasterApi.domain.model.Task;
import com.taskMasterApi.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}
