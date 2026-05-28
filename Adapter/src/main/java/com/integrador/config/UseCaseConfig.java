package com.integrador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.input.CreateProjectInput;
import project.input.DeleteProjectInput;
import project.input.FindProjectInput;
import project.input.UpdateProjectInput;
import project.output.ProjectOutPut;
import project.usecase.CreateProjectUseCase;
import project.usecase.DeleteProjectUseCase;
import project.usecase.FindProjectUseCase;
import project.usecase.UpdateProjectUseCase;
import task.input.CreateTaskInput;
import task.input.DeleteTaskInput;
import task.input.FindTaskInput;
import task.input.UpdateTaskInput;
import task.output.TaskOutPut;
import task.usecase.CreateTaskUseCase;
import task.usecase.DeleteTaskUseCase;
import task.usecase.FindTaskUseCase;
import task.usecase.UpdateTaskUseCase;

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
    public UpdateProjectInput updateProject(ProjectOutPut projectOutPut) {
        return new UpdateProjectUseCase(projectOutPut);
    }

    @Bean
    public UpdateTaskInput updateTask(
            TaskOutPut taskOutPut,
            ProjectOutPut projectOutPut,
            Clock clock
    ) {
        return new UpdateTaskUseCase(taskOutPut, projectOutPut, clock);
    }

    @Bean
    public FindTaskInput findTask(
            TaskOutPut taskOutPut,
            ProjectOutPut projectOutPut
    ) {
        return new FindTaskUseCase(taskOutPut, projectOutPut);
    }

    @Bean
    public FindProjectInput findProjects(ProjectOutPut projectOutPut) {
        return new FindProjectUseCase(projectOutPut);
    }
}
