package ca.saultcollege.csd215.todo;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoListTest {

    @Test
    void addTask_shouldIncreaseListSize() {
        TodoList list = new TodoList();
        list.addTask("Study Java");
        assertEquals(1, list.size());
    }

    @Test
    void completeTask_shouldMarkAsDone() {
        TodoList list = new TodoList();
        list.addTask("Finish lab");
        list.completeTask(1);
        assertTrue(list.getTasks().get(0).isCompleted());
    }

    @Test
    void removeCompleted_shouldDeleteFinishedTasks() {
        TodoList list = new TodoList();
        list.addTask("Task 1");
        list.addTask("Task 2");
        list.completeTask(2);
        int removed = list.removeCompleted();
        assertEquals(1, removed);
        assertEquals(1, list.size());
    }

    @Test
    void getTasks_shouldReturnUnmodifiableList() {
        TodoList list = new TodoList();
        list.addTask("Read book");
        List<Task> tasks = list.getTasks();
        assertThrows(UnsupportedOperationException.class, () -> tasks.add(new Task("New")));
    }
}