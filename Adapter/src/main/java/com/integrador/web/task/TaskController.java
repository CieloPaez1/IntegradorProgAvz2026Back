package com.integrador.web.task;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.input.CreateTaskInput;
import task.input.DeleteTaskInput;
import task.input.FindTaskInput;
import task.model.Task;

import java.util.List;

@RestController
public class TaskController {

    private final CreateTaskInput createTask;
    private final DeleteTaskInput deleteTask;
    private final FindTaskInput findTask;

    public TaskController(
            CreateTaskInput createTask,
            DeleteTaskInput deleteTask,
            FindTaskInput findTask
    ) {
        this.createTask = createTask;
        this.deleteTask = deleteTask;
        this.findTask = findTask;
    }

    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<TaskResponseDTO> createTask(
            @PathVariable Long projectId,
            @Valid @RequestBody TaskDTO request
    ) {
        Task result = createTask.createTask(
                projectId,
                request.getTitle(),
                request.getEstimateHours(),
                request.getAssignee(),
                request.getStatus()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TaskResponseDTO.from(result));
    }

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId
    ) {
        deleteTask.deleteTask(projectId, taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponseDTO>> findTasks(
            @RequestParam(required = false) Integer minEstimate,
            @RequestParam(required = false) String assignee
    ) {
        List<TaskResponseDTO> result = findTask.findTasks(minEstimate, assignee)
                .stream()
                .map(TaskResponseDTO::from)
                .toList();

        return ResponseEntity.ok(result);
    }
}