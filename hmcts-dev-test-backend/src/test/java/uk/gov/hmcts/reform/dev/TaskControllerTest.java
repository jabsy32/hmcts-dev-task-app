package uk.gov.hmcts.reform.dev;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.reform.dev.controllers.TaskController;
import uk.gov.hmcts.reform.dev.dtos.CreateTaskRequest;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.service.TaskService;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private CreateTaskRequest validRequest;

    @BeforeEach
    void setUp() {
        validRequest = new CreateTaskRequest();
        validRequest.setTitle("Test Task");
        validRequest.setDescription("Task description");
        validRequest.setStatus("OPEN");
        validRequest.setDueDate(java.time.LocalDateTime.of(2025, 12, 6, 14, 30));
    }

    //controller should return correct task when a valid request is submitted
    //creates a mock Task object and uses mocked TaskService.createTask method
    @Test
    void createTask_ShouldReturnCreatedTask() {

        Task returnedTask = new Task();
        returnedTask.setId(1L);
        returnedTask.setTitle(validRequest.getTitle());
        returnedTask.setDescription(validRequest.getDescription());
        returnedTask.setStatus(validRequest.getStatus());
        returnedTask.setDueDate(validRequest.getDueDate());

        when(taskService.createTask(any(CreateTaskRequest.class))).thenReturn(returnedTask);

        // Act
        ResponseEntity<Task> response = taskController.createTask(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        Task body = response.getBody();
        assertNotNull(body);
        assertEquals(1L, body.getId());
        assertEquals("Test Task", body.getTitle());
        assertEquals("Task description", body.getDescription());
        assertEquals("OPEN", body.getStatus());
        assertEquals(validRequest.getDueDate(), body.getDueDate());

        verify(taskService, times(1)).createTask(any(CreateTaskRequest.class));
    }

    //Controller should still work with empty or null description field
    @Test
    void createTask_ShouldCallServiceEvenWithEmptyDescription() {

        validRequest.setDescription(null);

        Task returnedTask = new Task();
        returnedTask.setId(2L);
        returnedTask.setTitle(validRequest.getTitle());
        returnedTask.setStatus(validRequest.getStatus());
        returnedTask.setDueDate(validRequest.getDueDate());

        when(taskService.createTask(any(CreateTaskRequest.class))).thenReturn(returnedTask);

        // Act
        ResponseEntity<Task> response = taskController.createTask(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(taskService, times(1)).createTask(any(CreateTaskRequest.class));
    }
}
