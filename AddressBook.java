import java.util.ArrayList;
import java.util.List;
public class AddressBook {
    private List<Contact> contacts;
    public AddressBook() {
        contacts = new ArrayList<>();
    }
    // UC7: Duplicate check using Streams
    public void addContact(Contact contact) {
        boolean isDuplicate = contacts.stream()
                .anyMatch(c -> c.equals(contact));

        if (isDuplicate) {
            System.out.println("Duplicate contact! Not added.");
            return;
        }

        contacts.add(contact);
        System.out.println("Contact added successfully!");
    }

    public Contact findContact(String firstName, String lastName) {
        return contacts.stream()
                .filter(c ->
                        c.getFirstName().equalsIgnoreCase(firstName) &&
                                c.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    public void deleteContact(String firstName, String lastName) {
        Contact contact = findContact(firstName, lastName);
        if (contact != null) {
            contacts.remove(contact);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        contacts.forEach(System.out::println);
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}