package ca.saultcollege.csd215.todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void newTask_isNotCompleted_andStoresTrimmedDescription() {
        // Arrange & Act
        Task t = new Task("  Review JUnit  ");
        // Assert
        assertEquals("Review JUnit", t.getDescription(), "should trim description");
        assertFalse(t.isCompleted(), "new task should not be completed");
    }

    @Test
    void complete_marksTaskAsCompleted() {
        Task t = new Task("Finish Lab 1");
        t.complete();
        assertTrue(t.isCompleted(), "task should be completed after calling complete()");
    }

    @Test
    void constructor_throwsOnNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null));
        assertThrows(IllegalArgumentException.class, () -> new Task(""));
        assertThrows(IllegalArgumentException.class, () -> new Task("   "));
    }
}