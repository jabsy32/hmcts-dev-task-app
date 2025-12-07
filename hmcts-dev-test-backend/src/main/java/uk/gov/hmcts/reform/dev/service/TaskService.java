package uk.gov.hmcts.reform.dev.service;

import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dev.dtos.CreateTaskRequest;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.repository.TaskRepository;

/**
 * Handles all business logic for tasks and interacts with the repository.
 * Converts incoming DTOs into Task entities before saving.
 */


@Service
public class TaskService {

    private final TaskRepository repository;


    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask (CreateTaskRequest request){

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        return repository.save(task);

    }
}
