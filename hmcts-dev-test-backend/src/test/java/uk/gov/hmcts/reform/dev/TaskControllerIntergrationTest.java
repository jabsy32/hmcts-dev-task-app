package uk.gov.hmcts.reform.dev;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.hmcts.reform.dev.dtos.CreateTaskRequest;
import uk.gov.hmcts.reform.dev.repository.TaskRepository;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll(); // to reset the database before each test
    }

    // To verify the "/tasks" endpoint handles valid task creation request
    // Excepts HTTP 200 ok response, id saved and correct title
    @Test
    void createTask_ValidRequest_Returns200() throws Exception {
        CreateTaskRequest request = new CreateTaskRequest();
        request.setTitle("Integration Task");
        request.setStatus("OPEN");
        request.setDueDate(LocalDateTime.of(2025, 12, 6, 14, 30));

        mockMvc.perform(post("/tasks")
                            .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.title").value("Integration Task"));
    }
}
