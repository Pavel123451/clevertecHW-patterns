package ru.clevertec.command;

import ru.clevertec.model.Task;
import ru.clevertec.repository.TaskRepository;

public abstract class Command {
    protected TaskRepository taskRepository;
    protected Task task;

    public Command(TaskRepository taskRepository, Task task) {
        this.taskRepository = taskRepository;
        this.task = task;
    }

    public abstract void execute();
    public abstract void undo();
}