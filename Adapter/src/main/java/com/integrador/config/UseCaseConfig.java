package com.integrador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.input.CreateProjectInput;
import project.input.DeleteProjectInput;
import project.output.ProjectOutPut;
import project.usecase.CreateProjectUseCase;
import project.usecase.DeleteProjectUseCase;
import task.input.CreateTaskInput;
import task.input.DeleteTaskInput;
import task.input.FindTaskInput;
import task.output.TaskOutPut;
import task.usecase.CreateTaskUseCase;
import task.usecase.DeleteTaskUseCase;
import task.usecase.FindTaskUseCase;

import java.time.Clock;

@Configuration
public class UseCaseConfig {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public CreateProjectInput createProject(ProjectOutPut projectOutPut) {
        return new CreateProjectUseCase(projectOutPut);
    }

    @Bean
    public DeleteProjectInput deleteProject(
            ProjectOutPut projectOutPut,
            TaskOutPut taskOutPut
    ) {
        return new DeleteProjectUseCase(projectOutPut, taskOutPut);
    }

    @Bean
    public CreateTaskInput createTask(
            TaskOutPut taskOutPut,
            ProjectOutPut projectOutPut,
            Clock clock
    ) {
        return new CreateTaskUseCase(taskOutPut, projectOutPut, clock);
    }

    @Bean
    public DeleteTaskInput deleteTask(TaskOutPut taskOutPut) {
        return new DeleteTaskUseCase(taskOutPut);
    }

    @Bean
    public FindTaskInput findTask(TaskOutPut taskOutPut) {
        return new FindTaskUseCase(taskOutPut);
    }
}