package ca.saultcollege.csd215.todo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class TodoListPersistenceTest {

    @TempDir
    Path tempDir; // temporary folder for tests

    @Test
    void saveAndLoad_shouldWork() throws IOException {
        TodoList list = new TodoList();
        list.addTask("Task 1");
        list.addTask("Task 2");
        list.completeTask(2);

        Path file = tempDir.resolve("todo.txt");
        list.save(file);

        TodoList loaded = new TodoList();
        loaded.load(file);

        assertEquals(2, loaded.size());
        assertFalse(loaded.getTasks().get(0).isCompleted());
        assertTrue(loaded.getTasks().get(1).isCompleted());
    }
}