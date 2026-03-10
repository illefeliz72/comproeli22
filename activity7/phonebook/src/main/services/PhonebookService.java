package com.phonebook.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.phonebook.models.Contact;

public class PhonebookService {
    private HashMap<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact c) {
        contacts.put(c.getName().toLowerCase(), c);
    }

    public Contact searchName(String name) {
        return contacts.get(name.toLowerCase());
    }

    public boolean removeContact(String name) {
        return contacts.remove(name.toLowerCase()) != null;
    }

    public Map<String, Contact> getAllContacts() {
        return contacts;
    }

    public void saveToCSV(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Contact c : contacts.values()) {
                bw.write(c.toCsvString());
                bw.newLine();
            }
            System.out.println("<=== Data successfully saved to  " + filename + "===>");
        } catch (IOException e) {
            System.err.println("<=== ERROR writing to file ===>" + e.getMessage());
        }
    }
}