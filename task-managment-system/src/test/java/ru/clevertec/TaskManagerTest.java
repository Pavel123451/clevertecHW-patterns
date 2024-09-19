package ru.clevertec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.command.Command;
import ru.clevertec.manager.TaskManager;
import ru.clevertec.model.Task;

import java.lang.reflect.Field;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void testAddTask() {
        Task task = new Task(1, "New Task");

        taskManager.addTask(task);

        assertEquals(task, taskManager.getTask(1));
        assertEquals(1, getCommandHistory().size());
    }

    @Test
    void testUpdateTask() {
        Task originalTask = new Task(1, "Original Task");
        Task updatedTask = new Task(1, "Updated Task");

        taskManager.addTask(originalTask);
        taskManager.updateTask(updatedTask);

        assertEquals(updatedTask, taskManager.getTask(1));
        assertEquals(2, getCommandHistory().size());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task(1, "Task to be deleted");

        taskManager.addTask(task);
        taskManager.deleteTask(1);

        assertNull(taskManager.getTask(1));
        assertEquals(2, getCommandHistory().size());
    }

    @Test
    void testUndoAddTask() {
        Task task = new Task(1, "Task to undo");

        taskManager.addTask(task);
        taskManager.undo();

        assertNull(taskManager.getTask(1));
        assertTrue(getCommandHistory().isEmpty());
    }

    @Test
    void testUndoUpdateTask() {
        Task originalTask = new Task(1, "Original Task");
        Task updatedTask = new Task(1, "Updated Task");

        taskManager.addTask(originalTask);
        taskManager.updateTask(updatedTask);
        taskManager.undo();

        assertEquals(originalTask, taskManager.getTask(1));
        assertEquals(1, getCommandHistory().size());
    }

    @Test
    void testUndoDeleteTask() {
        Task task = new Task(1, "Task to undo");

        taskManager.addTask(task);
        taskManager.deleteTask(1);
        taskManager.undo();

        assertEquals(task, taskManager.getTask(1));
        assertEquals(1, getCommandHistory().size());
    }

    @SuppressWarnings("unchecked")
    private Stack<Command> getCommandHistory() {
        try {
            Field field = TaskManager.class.getDeclaredField("commandHistory");
            field.setAccessible(true);
            return (Stack<Command>) field.get(taskManager);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Unable to access commandHistory field", e);
        }
    }
}

