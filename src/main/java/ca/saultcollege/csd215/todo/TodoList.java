package ca.saultcollege.csd215.todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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

    // Save all tasks to a text file
    public void save(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            String flag = t.isCompleted() ? "1" : "0";
            String desc = t.getDescription().replace("\t", " ");
            lines.add(flag + "\t" + desc);
        }
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    // Load tasks from a text file
    public void load(Path path) throws IOException {
        tasks.clear();
        if (!Files.exists(path)) return;

        for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
            if (line == null || line.isBlank()) continue;

            int tab = line.indexOf('\t');
            if (tab <= 0) continue; // no flag or no TAB → skip

            char flag = line.charAt(0);               // '0' or '1'
            String desc = line.substring(tab + 1).trim();
            Task t = new Task(desc);
            if (flag == '1') t.complete();
            tasks.add(t);
        }
    }

    // Show all tasks with colors and symbols
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet!");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);

            // Colors: blue = pending, green = completed
            String color = t.isCompleted() ? "\u001B[32m" : "\u001B[34m";
            String box = t.isCompleted() ? "[✔]" : "[ ]";

            System.out.println(color + (i + 1) + ". " + box + " " + t.getDescription() + "\u001B[0m");
        }
    }
}