import java.io.*;
import java.util.*;

public class ToDoList {
    private static final String FILE_NAME = "tasks.txt";
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nTo-Do List Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter task: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    saveTasks();
                    break;
                case 2:
                    System.out.print("Enter task number to remove: ");
                    int index = scanner.nextInt();
                    if (index > 0 && index <= tasks.size()) {
                        tasks.remove(index - 1);
                        saveTasks();
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;
                case 3:
                    displayTasks();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            System.out.println("No previous tasks found.");
        }
    }

    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    private static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\nYour Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }
}
