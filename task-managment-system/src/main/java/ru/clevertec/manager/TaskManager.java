package ru.clevertec.manager;

import ru.clevertec.command.Command;
import ru.clevertec.command.impl.CreateTaskCommand;
import ru.clevertec.command.impl.DeleteTaskCommand;
import ru.clevertec.command.impl.UpdateTaskCommand;
import ru.clevertec.model.Task;
import ru.clevertec.repository.TaskRepository;

import java.util.EmptyStackException;
import java.util.Stack;


public class TaskManager {
    private final TaskRepository taskRepository = new TaskRepository();
    private final Stack<Command> commandHistory = new Stack<>();

    public void addTask(Task task) {
        Command command = new CreateTaskCommand(taskRepository, task);
        command.execute();
        commandHistory.push(command);
    }

    public void updateTask(Task updatedTask) {
        Command command = new UpdateTaskCommand(taskRepository, updatedTask);
        command.execute();
        commandHistory.push(command);
    }

    public void deleteTask(int id) {
        Task task = getTask(id);
        Command command = new DeleteTaskCommand(taskRepository, task);
        command.execute();
        commandHistory.push(command);
    }

    public Task getTask(int id) {
        return taskRepository.getTaskById(id);
    }

    public void undo() {
        if (commandHistory.isEmpty()) {
            throw new EmptyStackException();
        }
        Command command = commandHistory.pop();
        command.undo();

    }
}
