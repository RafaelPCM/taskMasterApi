package com.taskMasterApi.domain;

import com.taskMasterApi.domain.enums.StatusEnum;
import com.taskMasterApi.domain.model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskTest {

    @Test
    void testTaskCreation() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatusEnum(StatusEnum.TODO);

        assertNotNull(task);
        assertEquals("Test Task", task.getTitle());
        assertEquals("Test Description", task.getDescription());
        assertEquals(StatusEnum.TODO, task.getStatusEnum());
    }
}
