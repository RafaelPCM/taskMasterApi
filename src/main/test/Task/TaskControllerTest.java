import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.taskMasterApi.model.Task;
import com.taskMasterApi.service.TaskService;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setCompleted(false);
    }

    @Test
    void testGetAllTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetTaskById() throws Exception {
        Mockito.when(taskService.getTaskById(task.getId())).thenReturn(Optional.of(task));

        mockMvc.perform(get("/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    void testGetTaskByTitle() throws Exception {
        Mockito.when(taskService.getTaskByTitle(task.getTitle())).thenReturn(Optional.of(task));

        mockMvc.perform(get("/tasks/title/{title}", task.getTitle()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    void testCreateTask() throws Exception {
        Mockito.when(taskService.saveTask(Mockito.any(Task.class), Mockito.anyString())).thenReturn(task);

        mockMvc.perform(post("/tasks")
                .contentType("application/json")
                .content("{\"title\":\"New Task\", \"description\":\"New Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    void testUpdateTask() throws Exception {
        Task updatedTask = new Task();
        updatedTask.setId(task.getId());
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");

        Mockito.when(taskService.updateTask(Mockito.eq(task.getId()), Mockito.any(Task.class), Mockito.anyString()))
                .thenReturn(updatedTask);

        mockMvc.perform(put("/tasks/{id}", task.getId())
                .contentType("application/json")
                .content("{\"title\":\"Updated Task\", \"description\":\"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"));
    }

    @Test
    void testDeleteTask() throws Exception {
        mockMvc.perform(delete("/tasks/{id}", task.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testCompleteTask() throws Exception {
        task.setCompleted(true);
        Mockito.when(taskService.getTaskById(task.getId())).thenReturn(Optional.of(task));
        Mockito.when(taskService.saveTask(task, "username")).thenReturn(task);

        mockMvc.perform(patch("/tasks/{id}/complete", task.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed").value(true));
    }
}
