package com.integrador.web.project;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.input.CreateProjectInput;
import project.input.DeleteProjectInput;
import project.input.FindProjectInput;
import project.model.Project;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final CreateProjectInput createProject;
    private final DeleteProjectInput deleteProject;
    private final FindProjectInput findProjects;

    public ProjectController(
            CreateProjectInput createProject,
            DeleteProjectInput deleteProject,
            FindProjectInput findProjects
    ) {
        this.createProject = createProject;
        this.deleteProject = deleteProject;
        this.findProjects = findProjects;
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        List<ProjectResponseDTO> projects = findProjects.execute()
                .stream()
                .map(ProjectResponseDTO::from)
                .toList();
        return ResponseEntity.ok(projects);
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(
            @Valid @RequestBody ProjectDTO request
    ) {
        Project project = Project.create(
                request.getName(),
                request.getStartDate(),
                request.getEndDate(),
                request.getStatus(),
                request.getDescription()
        );

        Project result = createProject.createProject(project);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProjectResponseDTO.from(result));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long projectId
    ) {
        deleteProject.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}