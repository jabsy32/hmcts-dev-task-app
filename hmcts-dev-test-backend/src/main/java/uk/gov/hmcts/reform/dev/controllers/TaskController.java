package uk.gov.hmcts.reform.dev.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.hmcts.reform.dev.dtos.CreateTaskRequest;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // used to verify connection in tests, GET /tasks/home
    @GetMapping("/home")
    public String home(){
        return "Welcome";
    }

    /**
     * Creates a new task
     * validates the request body and uses task service for task creation

     * POST /tasks
     * @param request DTO containing title, status, dueDate
     * @return the created Task with 200 OK
      */

    @PostMapping
    public ResponseEntity<Task> createTask (@Valid @RequestBody CreateTaskRequest request){

        Task task = service.createTask(request);
        return ResponseEntity.ok(task);

    }

}
