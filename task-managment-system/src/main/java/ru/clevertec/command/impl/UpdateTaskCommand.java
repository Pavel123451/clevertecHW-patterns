package ru.clevertec.command.impl;

import ru.clevertec.command.Command;
import ru.clevertec.model.Task;
import ru.clevertec.repository.TaskRepository;

public class UpdateTaskCommand extends Command {
    private Task updatedTask;

    public UpdateTaskCommand(TaskRepository taskRepository, Task updatedTask) {
        super(taskRepository, updatedTask);
        this.updatedTask = updatedTask;
        this.task = taskRepository.getTaskById(updatedTask.getId());
    }

    @Override
    public void execute() {
        taskRepository.updateTask(updatedTask);
        System.out.println("Task updated: " + updatedTask);
    }

    @Override
    public void undo() {
        taskRepository.updateTask(this.task);
        System.out.println("Task update undone: " + this.task);
    }
}
