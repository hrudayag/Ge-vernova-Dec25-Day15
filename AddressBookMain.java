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
            System.out.println("6. Count Contacts by City");
            System.out.println("7. Count Contacts by State");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                // UC6
                case 1:
                    System.out.print("Enter Address Book name: ");
                    String name = scanner.nextLine();
                    addressBookMap.putIfAbsent(name, new AddressBook());
                    System.out.println("Address Book ready.");
                    break;

                // UC1–UC5, UC11
                case 2:
                    System.out.print("Enter Address Book name: ");
                    String bookName = scanner.nextLine();
                    AddressBook book = addressBookMap.get(bookName);

                    if (book == null) {
                        System.out.println("Address Book not found!");
                        break;
                    }

                    boolean inside = true;
                    while (inside) {
                        System.out.println("\nAddress Book Menu:");
                        System.out.println("1. Add Contact");
                        System.out.println("2. Edit Contact");
                        System.out.println("3. Delete Contact");
                        System.out.println("4. Display Contacts");
                        System.out.println("5. Sort Contacts by Name");
                        System.out.println("6. Sort Contacts by City");
                        System.out.println("7. Sort Contacts by State");
                        System.out.println("8. Sort Contacts by Zip");
                        System.out.println("9. Write Contacts to File");
                        System.out.println("10. Read Contacts from File");
                        System.out.println("11. Back");
                        System.out.print("Choose an option: ");


                        int ch = scanner.nextInt();
                        scanner.nextLine();

                        switch (ch) {
                            case 1:
                                System.out.print("First Name: ");
                                String fn = scanner.nextLine();
                                System.out.print("Last Name: ");
                                String ln = scanner.nextLine();
                                System.out.print("Address: ");
                                String addr = scanner.nextLine();
                                System.out.print("City: ");
                                String city = scanner.nextLine();
                                System.out.print("State: ");
                                String state = scanner.nextLine();
                                System.out.print("Zip: ");
                                String zip = scanner.nextLine();
                                System.out.print("Phone: ");
                                String phone = scanner.nextLine();
                                System.out.print("Email: ");
                                String email = scanner.nextLine();

                                book.addContact(new Contact(fn, ln, addr, city, state, zip, phone, email));
                                break;

                            case 2:
                                System.out.print("First Name: ");
                                String ef = scanner.nextLine();
                                System.out.print("Last Name: ");
                                String el = scanner.nextLine();
                                Contact c = book.findContact(ef, el);

                                if (c == null) {
                                    System.out.println("Contact not found!");
                                    break;
                                }

                                System.out.print("New City: ");
                                c.setCity(scanner.nextLine());
                                System.out.println("Updated!");
                                break;

                            case 3:
                                System.out.print("First Name: ");
                                String df = scanner.nextLine();
                                System.out.print("Last Name: ");
                                String dl = scanner.nextLine();
                                book.deleteContact(df, dl);
                                break;

                            case 4:
                                book.displayAllContacts();
                                break;

                            case 5:
                                book.sortByName();
                                break;

                            case 6:
                                book.sortByCity();
                                break;

                            case 7:
                                book.sortByState();
                                break;

                            case 8:
                                book.sortByZip();
                                break;

                            case 9:
                                System.out.print("Enter file name: ");
                                String fileName = scanner.nextLine();
                                book.writeToFile(fileName);
                                break;

                            case 10:
                                System.out.print("Enter file name: ");
                                String readFile = scanner.nextLine();
                                book.readFromFile(readFile);
                                break;
                            case 11:
                                inside = false;
                                break;
                        }
                    }
                    break;

                // UC8
                case 3:
                    System.out.print("Search by (city/state): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter value: ");
                    String val = scanner.nextLine();

                    List<Contact> result = addressBookMap.values().stream()
                            .flatMap(b -> b.getContacts().stream())
                            .filter(c -> type.equalsIgnoreCase("city")
                                    ? c.getCity().equalsIgnoreCase(val)
                                    : c.getState().equalsIgnoreCase(val))
                            .toList();

                    if (result.isEmpty())
                        System.out.println("No person found.");
                    else
                        result.forEach(System.out::println);
                    break;

                // UC9
                case 4:
                    addressBookMap.values().stream()
                            .flatMap(b -> b.getContacts().stream())
                            .collect(Collectors.groupingBy(Contact::getCity))
                            .forEach((k, v) -> {
                                System.out.println("\nCity: " + k);
                                v.forEach(System.out::println);
                            });
                    break;

                case 5:
                    addressBookMap.values().stream()
                            .flatMap(b -> b.getContacts().stream())
                            .collect(Collectors.groupingBy(Contact::getState))
                            .forEach((k, v) -> {
                                System.out.println("\nState: " + k);
                                v.forEach(System.out::println);
                            });
                    break;

                // UC10
                case 6:
                    addressBookMap.values().stream()
                            .flatMap(b -> b.getContacts().stream())
                            .collect(Collectors.groupingBy(
                                    Contact::getCity,
                                    Collectors.counting()))
                            .forEach((k, v) -> System.out.println(k + " -> " + v));
                    break;

                case 7:
                    addressBookMap.values().stream()
                            .flatMap(b -> b.getContacts().stream())
                            .collect(Collectors.groupingBy(
                                    Contact::getState,
                                    Collectors.counting()))
                            .forEach((k, v) -> System.out.println(k + " -> " + v));
                    break;

                case 8:
                    running = false;
                    System.out.println("Exiting. Thank you!");
                    break;
            }
        }
        scanner.close();
    }
}
