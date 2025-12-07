package uk.gov.hmcts.reform.dev.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// DTO used to separate API layer from the entity model and to validate incoming requests.

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateTaskRequest {

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String status;

    @NotNull
    private LocalDateTime dueDate;
}
