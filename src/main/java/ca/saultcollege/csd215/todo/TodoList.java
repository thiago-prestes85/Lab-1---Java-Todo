package ca.saultcollege.csd215.todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoList {
    // List to store all tasks
    private final List<Task> tasks = new ArrayList<>();

    // Add a new task
    public Task addTask(String description) {
        Task t = new Task(description);
        tasks.add(t);
        return t;
    }

    // Mark a task as completed using 1-based index
    public void completeTask(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        tasks.get(index - 1).complete();
    }

    // Remove all completed tasks
    public int removeCompleted() {
        int before = tasks.size();
        tasks.removeIf(Task::isCompleted);
        return before - tasks.size();
    }

    // Return all tasks (read-only)
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    // Return total number of tasks
    public int size() {
        return tasks.size();
    }
}