package com.phonebook;

import java.util.Scanner;

import com.phonebook.models.Contact;
import com.phonebook.services.PhonebookService;

public class App {
    public static void main(String[] args) {
        PhonebookService service = new PhonebookService();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("""
                    <===   1. Add | 2. Search | 3. Remove | 4. Display All | 5. Save to CSV | 0. Exit ===>
                        """);
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    service.addContact(new Contact(name, phone, email));
                    break;
                case 2:
                    System.out.print("Search Name: ");
                    Contact found = service.searchName(sc.nextLine());
                    System.out.println(found != null ? "Found: " + found.toCsvString() : "Contact Not Found.");
                    break;
                case 3:
                    System.out.print("Remove Name: ");
                    boolean removed = service.removeContact(sc.nextLine());
                    System.out.println(removed ? "Removed." : "Contact Not Found.");
                    break;
                case 4:
                    service.getAllContacts().values().forEach(c -> System.out.println(c.toCsvString()));
                    break;
                case 5:
                    service.saveToCSV("contacts.csv");
                    break;
            }
        } while (choice != 0);
    }
}