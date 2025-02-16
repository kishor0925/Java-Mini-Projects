import java.io.*;
import java.util.*;

class Contact implements Serializable {
    String name, phone, email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}

public class ContactManagement {
    private static final String FILE_NAME = "contacts.ser";
    private static List<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        loadContacts();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nContact Management System:");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    contacts.add(new Contact(name, phone, email));
                    saveContacts();
                    break;
                case 2:
                    displayContacts();
                    break;
                case 3:
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    searchContact(searchName);
                    break;
                case 4:
                    System.out.print("Enter contact number to delete: ");
                    int index = scanner.nextInt();
                    if (index > 0 && index <= contacts.size()) {
                        contacts.remove(index - 1);
                        saveContacts();
                    } else {
                        System.out.println("Invalid contact number.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void loadContacts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            contacts = (List<Contact>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous contacts found.");
        }
    }

    private static void saveContacts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Error saving contacts.");
        }
    }

    private static void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            System.out.println("\nYour Contacts:");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }
        }
    }

    private static void searchContact(String name) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.name.equalsIgnoreCase(name)) {
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Contact not found.");
        }
    }
}
