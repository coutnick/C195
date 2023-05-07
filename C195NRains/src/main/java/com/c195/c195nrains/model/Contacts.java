package com.c195.c195nrains.model;

public class Contacts {

    private final int contactId;
    private final String contactName;
    private final String email;

    public Contacts(int pContactId, String pContactName, String pEmail) {
        contactId = pContactId;
        contactName = pContactName;
        email = pEmail;
    }

    public int getContactId() {
        return contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }
}
