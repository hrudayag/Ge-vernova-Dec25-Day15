import java.util.*;
import java.util.stream.Collectors;
public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        Scanner scanner = new Scanner(System.in);
        Map<String, AddressBook> addressBookMap = new HashMap<>();
        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add New Address Book");
            System.out.println("2. Select Address Book");
            System.out.println("3. Search Person by City or State");
            System.out.println("4. View Persons by City");
            System.out.println("5. View Persons by State");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {

                case 1:
                    System.out.print("Enter unique Address Book name: ");
                    String bookName = scanner.nextLine();

                    if (addressBookMap.containsKey(bookName)) {
                        System.out.println("Address Book already exists!");
                    } else {
                        addressBookMap.put(bookName, new AddressBook());
                        System.out.println("Address Book '" + bookName + "' created successfully!");
                    }
                    break;

                case 2:
                    System.out.print("Enter Address Book name: ");
                    String selectedBook = scanner.nextLine();

                    AddressBook addressBook = addressBookMap.get(selectedBook);

                    if (addressBook == null) {
                        System.out.println("Address Book not found!");
                        break;
                    }

                    boolean inAddressBook = true;
                    while (inAddressBook) {
                        System.out.println("\nAddress Book Menu (" + selectedBook + "):");
                        System.out.println("1. Add Contact");
                        System.out.println("2. Edit Contact");
                        System.out.println("3. Delete Contact");
                        System.out.println("4. Display Contacts");
                        System.out.println("5. Back to Main Menu");
                        System.out.print("Choose an option: ");

                        int abChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (abChoice) {
                            case 1:
                                System.out.print("Enter First Name: ");
                                String firstName = scanner.nextLine();
                                System.out.print("Enter Last Name: ");
                                String lastName = scanner.nextLine();
                                System.out.print("Enter Address: ");
                                String address = scanner.nextLine();
                                System.out.print("Enter City: ");
                                String city = scanner.nextLine();
                                System.out.print("Enter State: ");
                                String state = scanner.nextLine();
                                System.out.print("Enter Zip: ");
                                String zip = scanner.nextLine();
                                System.out.print("Enter Phone Number: ");
                                String phone = scanner.nextLine();
                                System.out.print("Enter Email: ");
                                String email = scanner.nextLine();

                                Contact contact = new Contact(
                                        firstName, lastName, address,
                                        city, state, zip, phone, email
                                );
                                addressBook.addContact(contact);
                                break;

                            case 2:
                                System.out.print("Enter First Name of contact to edit: ");
                                String ef = scanner.nextLine();
                                System.out.print("Enter Last Name of contact to edit: ");
                                String el = scanner.nextLine();

                                Contact contactToEdit = addressBook.findContact(ef, el);

                                if (contactToEdit != null) {
                                    System.out.println("Contact found: " + contactToEdit);

                                    System.out.println("\nWhat would you like to edit?");
                                    System.out.println("1. Address");
                                    System.out.println("2. City");
                                    System.out.println("3. State");
                                    System.out.println("4. Zip");
                                    System.out.println("5. Phone Number");
                                    System.out.println("6. Email");
                                    System.out.print("Choose an option: ");

                                    int editChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (editChoice) {
                                        case 1:
                                            System.out.print("Enter new Address: ");
                                            contactToEdit.setAddress(scanner.nextLine());
                                            break;
                                        case 2:
                                            System.out.print("Enter new City: ");
                                            contactToEdit.setCity(scanner.nextLine());
                                            break;
                                        case 3:
                                            System.out.print("Enter new State: ");
                                            contactToEdit.setState(scanner.nextLine());
                                            break;
                                        case 4:
                                            System.out.print("Enter new Zip: ");
                                            contactToEdit.setZip(scanner.nextLine());
                                            break;
                                        case 5:
                                            System.out.print("Enter new Phone Number: ");
                                            contactToEdit.setPhoneNumber(scanner.nextLine());
                                            break;
                                        case 6:
                                            System.out.print("Enter new Email: ");
                                            contactToEdit.setEmail(scanner.nextLine());
                                            break;
                                        default:
                                            System.out.println("Invalid option!");
                                    }
                                    System.out.println("Contact updated successfully!");
                                } else {
                                    System.out.println("Contact not found!");
                                }
                                break;

                            case 3:
                                System.out.print("Enter First Name: ");
                                String df = scanner.nextLine();
                                System.out.print("Enter Last Name: ");
                                String dl = scanner.nextLine();
                                addressBook.deleteContact(df, dl);
                                break;

                            case 4:
                                addressBook.displayAllContacts();
                                break;

                            case 5:
                                inAddressBook = false;
                                break;

                            default:
                                System.out.println("Invalid option!");
                        }
                    }
                    break;
                case 3:
                    System.out.print("Search by (city/state): ");
                    String type = scanner.nextLine().toLowerCase();

                    System.out.print("Enter value: ");
                    String value = scanner.nextLine();

                    List<Contact> results = addressBookMap.values().stream()
                            .flatMap(book -> book.getContacts().stream())
                            .filter(c -> type.equals("city")
                                    ? c.getCity().equalsIgnoreCase(value)
                                    : c.getState().equalsIgnoreCase(value))
                            .toList();

                    if (results.isEmpty()) {
                        System.out.println(
                                "No person found for " + type + " = " + value
                        );
                    } else {
                        results.forEach(System.out::println);
                    }
                    break;


                case 4:
                    Map<String, List<Contact>> personsByCity =
                            addressBookMap.values().stream()
                                    .flatMap(book -> book.getContacts().stream())
                                    .collect(Collectors.groupingBy(Contact::getCity));

                    if (personsByCity.isEmpty()) {
                        System.out.println("No contacts available.");
                    } else {
                        personsByCity.forEach((city, persons) -> {
                            System.out.println("\nCity: " + city);
                            persons.forEach(System.out::println);
                        });
                    }
                    break;
                case 5:
                    Map<String, List<Contact>> personsByState =
                            addressBookMap.values().stream()
                                    .flatMap(book -> book.getContacts().stream())
                                    .collect(Collectors.groupingBy(Contact::getState));

                    if (personsByState.isEmpty()) {
                        System.out.println("No contacts available.");
                    } else {
                        personsByState.forEach((state, persons) -> {
                            System.out.println("\nState: " + state);
                            persons.forEach(System.out::println);
                        });
                    }
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting system. Thank you!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
        scanner.close();
    }
}