package ca.saultcollege.csd215.todo;

/**
 * Represents a single todo task with a description and completion status.
 */
public class Task {
    private final String description;
    private boolean completed;

    /**
     * Creates a new Task.
     * @param description non-null, non-blank text describing the task
     * @throws IllegalArgumentException if description is null or blank
     */
    public Task(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("description must not be null or blank");
        }
        this.description = description.trim();
        this.completed = false;
    }

    /** @return the task description (never null or blank) */
    public String getDescription() {
        return description;
    }

    /** @return true if the task is completed */
    public boolean isCompleted() {
        return completed;
    }

    /** Marks this task as completed. */
    public void complete() {
        this.completed = true;
    }

    @Override
    public String toString() {
        // Example: "[ ] Buy milk" or "[✓] Buy milk"
        String box = completed ? "[\u2713]" : "[ ]";
        return box + " " + description;
    }
}









