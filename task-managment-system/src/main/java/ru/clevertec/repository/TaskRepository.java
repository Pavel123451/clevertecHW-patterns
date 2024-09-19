package ru.clevertec.repository;

import ru.clevertec.model.Task;

import java.util.HashMap;
import java.util.Map;

public class TaskRepository {
    private final Map<Integer, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateTask(Task task) {
        if (!tasks.containsKey(task.getId())) {
            throw new IllegalArgumentException(" Updated task does not exist");
        }
        tasks.put(task.getId(), task);

    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }
}
