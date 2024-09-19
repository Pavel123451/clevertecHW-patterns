package ru.clevertec.command.impl;

import ru.clevertec.command.Command;
import ru.clevertec.model.Task;
import ru.clevertec.repository.TaskRepository;

public class CreateTaskCommand extends Command {
    public CreateTaskCommand(TaskRepository taskRepository, Task task) {
        super(taskRepository, task);
    }

    @Override
    public void execute() {
        taskRepository.addTask(task);
        System.out.println("Task created: " + task);
    }

    @Override
    public void undo() {
        taskRepository.deleteTask(task.getId());
        System.out.println("Task creation undone: " + task);
    }
}

