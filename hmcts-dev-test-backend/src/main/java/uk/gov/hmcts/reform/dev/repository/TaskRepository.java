package uk.gov.hmcts.reform.dev.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmcts.reform.dev.models.Task;

//In-memory database, JPA repository providing CRUD operations for Task entities.

public interface TaskRepository extends JpaRepository<Task, Long> {
}
