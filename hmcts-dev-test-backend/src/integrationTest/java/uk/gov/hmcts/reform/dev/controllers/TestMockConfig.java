package uk.gov.hmcts.reform.dev.controllers;


import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import uk.gov.hmcts.reform.dev.service.TaskService;

@TestConfiguration
public class TestMockConfig {

    @Bean
    public TaskService taskService() {
        return Mockito.mock(TaskService.class);
    }
}
