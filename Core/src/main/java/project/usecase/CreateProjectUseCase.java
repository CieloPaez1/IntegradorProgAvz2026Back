package project.usecase;

import exception.DuplicateResourceException;
import exception.ValidationException;
import project.input.CreateProjectInput;
import project.model.Project;
import project.output.ProjectOutPut;

public class CreateProjectUseCase implements CreateProjectInput {

    private final ProjectOutPut projectOutput;

    public CreateProjectUseCase(ProjectOutPut projectOutput) {
        this.projectOutput = projectOutput;
    }

    @Override
    public Project createProject(Project project) {

        if (projectOutput.existsByName(project.getName())) {
            throw new DuplicateResourceException("Project name already exists");
        }

        Boolean saved = projectOutput.save(project);

        if (saved == null || !saved) {
            throw new ValidationException("Failed to save project");
        }

        return project;
    }
}