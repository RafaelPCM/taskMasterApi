package com.taskMasterApi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.taskMasterApi.domain.model.Task;
import com.taskMasterApi.domain.enums.StatusEnum;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatusEnum(StatusEnum.TODO);
        taskRepository.save(task);
    }

    @Test
    void testFindByTaskId() {
        Optional<Task> foundTask = taskRepository.findByTaskId(task.getId());
        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getTitle()).isEqualTo("Test Task");
    }

    @Test
    void testFindByTitle() {
        Optional<Task> foundTask = taskRepository.findByTitle("Test Task");
        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getDescription()).isEqualTo("Test Description");
    }

    @Test
    void testFindByTaskId_NotFound() {
        Optional<Task> foundTask = taskRepository.findByTaskId(999L);
        assertThat(foundTask).isNotPresent();
    }

    @Test
    void testFindByTitle_NotFound() {
        Optional<Task> foundTask = taskRepository.findByTitle("Non-existent Task");
        assertThat(foundTask).isNotPresent();
    }
}
