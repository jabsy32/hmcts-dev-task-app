package uk.gov.hmcts.reform.dev;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.gov.hmcts.reform.dev.dtos.CreateTaskRequest;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.repository.TaskRepository;
import uk.gov.hmcts.reform.dev.service.TaskService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    //Creates a sample requestDTO and what should be returned from the database
    @Test
    void createTask_ReturnTask(){

        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("Test Task");
        request.setDescription("A test task");
        request.setStatus("OPEN");
        request.setDueDate(LocalDateTime.of(2025, 12, 6, 14, 30));

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle(request.getTitle());
        savedTask.setDescription(request.getDescription());
        savedTask.setStatus(request.getStatus());
        savedTask.setDueDate(request.getDueDate());

        when(repository.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.createTask(request);

        assertNotNull(result);
        assertEquals(savedTask.getId(), result.getId());
        assertEquals("Test Task", result.getTitle());
        assertEquals("A test task", result.getDescription());
        assertEquals("OPEN", result.getStatus());
        assertEquals(LocalDateTime.of(2025, 12, 6, 14, 30), result.getDueDate());

        verify(repository, times(1)).save(any(Task.class));

    }
}
