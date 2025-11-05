package ca.saultcollege.csd215.todo;

// Simple task model
public class Task {
    private final String description;
    private boolean completed;

    public Task(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be blank");
        }
        this.description = description.trim();
        this.completed = false;
    }

    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public void complete() { this.completed = true; }
}