package task.output;

import task.model.Task;

import java.util.List;

public interface TaskOutPut {

    boolean save(Task task);

    List<Task> findTasks(
            Integer minEstimate,
            String assignee
    );

    Task findById(Long taskId);

    boolean deleteById(Long taskId);

    boolean existsByTitle(String title);

    int countTasksByProjectId(Long projectId);
}