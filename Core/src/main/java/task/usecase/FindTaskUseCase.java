package task.usecase;

import task.input.FindTaskInput;
import task.model.Task;
import task.output.TaskOutPut;

import java.util.List;

public class FindTaskUseCase implements FindTaskInput {

    private final TaskOutPut taskOutput;

    public FindTaskUseCase(TaskOutPut taskOutput) {
        this.taskOutput = taskOutput;
    }

    @Override
    public List<Task> findTasks(Integer minEstimate, String assignee) {
        return taskOutput.findTasks(minEstimate, assignee);
    }
}




