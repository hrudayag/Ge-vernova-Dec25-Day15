import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;


public class AddressBook {

    private List<Contact> contacts = new ArrayList<>();

    //UC2,UC7
    public void addContact(Contact contact) {
        boolean duplicate = contacts.stream()
                .anyMatch(c -> c.equals(contact));

        if (duplicate) {
            System.out.println("Duplicate contact! Not added.");
            return;
        }
        contacts.add(contact);
        System.out.println("Contact added successfully!");
    }

    //UC3
    public Contact findContact(String firstName, String lastName) {
        return contacts.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(firstName)
                        && c.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    //UC4
    public void deleteContact(String firstName, String lastName) {
        Contact c = findContact(firstName, lastName);
        if (c != null) {
            contacts.remove(c);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    //UC5
    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        contacts.forEach(System.out::println);
    }

    //UC11
    public void sortByName() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to sort.");
            return;
        }

        contacts.stream()
                .sorted(Comparator.comparing(Contact::getFirstName)
                        .thenComparing(Contact::getLastName))
                .forEach(System.out::println);
    }
    // UC12: Sort by City
    public void sortByCity() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to sort.");
            return;
        }

        contacts.stream()
                .sorted(Comparator.comparing(Contact::getCity))
                .forEach(System.out::println);
    }

    // UC12: Sort by State
    public void sortByState() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to sort.");
            return;
        }

        contacts.stream()
                .sorted(Comparator.comparing(Contact::getState))
                .forEach(System.out::println);
    }

    // UC12: Sort by Zip
    public void sortByZip() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to sort.");
            return;
        }

        contacts.stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .forEach(System.out::println);
    }

    public List<Contact> getContacts() {
        return contacts;
    }
    public void writeToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Contact c : contacts) {
                writer.write(
                        c.getFirstName() + "," +
                                c.getLastName() + "," +
                                c.getCity() + "," +
                                c.getState() + "," +
                                c.getZip()
                );
                writer.newLine();
            }
            System.out.println("Contacts written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    public void readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\n--- Contacts from File ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
    }

}
