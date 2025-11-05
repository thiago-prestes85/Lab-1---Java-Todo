package ca.saultcollege.csd215.todo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    // ANSI color codes for terminal
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        TodoList todoList = new TodoList();

        // default file used by save/load (matches TodoList.save/load(Path))
        Path file = Path.of("tasks.txt");

        System.out.println(CYAN + "Welcome to the Java ToDo List App" + RESET);
        System.out.println(CYAN + "---------------------------------" + RESET);

        boolean running = true;

        while (running) {
            System.out.println("\n" + YELLOW + "Your tasks:" + RESET);
            todoList.listTasks();

            System.out.println("\n" + BLUE + "What would you like to do?" + RESET);
            System.out.println("a - add task");
            System.out.println("c - complete task");
            System.out.println("r - remove completed tasks");
            System.out.println("s - save tasks");
            System.out.println("l - load tasks");
            System.out.println("q - quit");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "a":
                    System.out.print("\n" + BLUE + "Enter task description\n> " + RESET);
                    String desc = scanner.nextLine().trim();
                    todoList.addTask(desc);
                    System.out.println(GREEN + "Task added!" + RESET);
                    break;

                case "c":
                    System.out.print("\n" + BLUE + "Enter the number of the task to complete\n> " + RESET);
                    int index = Integer.parseInt(scanner.nextLine());
                    // 1-based index as required by TodoList.completeTask
                    todoList.completeTask(index);
                    System.out.println(GREEN + "Task completed!" + RESET);
                    break;

                case "r":
                    todoList.removeCompleted();
                    System.out.println(GREEN + "Completed tasks removed!" + RESET);
                    break;

                case "s":
                    todoList.save(file);   // uses Path
                    System.out.println(GREEN + "Tasks saved!" + RESET);
                    break;

                case "l":
                    todoList.load(file);   // uses Path
                    System.out.println(GREEN + "Tasks loaded!" + RESET);
                    break;

                case "q":
                    running = false;
                    System.out.println(CYAN + "\nGood bye!" + RESET);
                    break;

                default:
                    System.out.println(YELLOW + "Unknown command" + RESET);
                    break;
            }
        }

        scanner.close();
    }
}