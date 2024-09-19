package ru.clevertec.command.impl;

import ru.clevertec.command.Command;
import ru.clevertec.model.Task;
import ru.clevertec.repository.TaskRepository;

public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand(TaskRepository taskRepository, Task task) {
        super(taskRepository, task);
    }

    @Override
    public void execute() {
        taskRepository.deleteTask(task.getId());
        System.out.println("Task deleted: " + task);
    }

    @Override
    public void undo() {
        taskRepository.addTask(task);
        System.out.println("Task deletion undone: " + task);
    }
}
