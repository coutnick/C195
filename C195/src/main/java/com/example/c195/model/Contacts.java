package com.example.c195.model;

public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    public Contacts(int pContactId, String pContactName, String pEmail) {
        contactId = pContactId;
        contactName = pContactName;
        email = pEmail;
    }

    public void setContactId(int pContactId) {
        contactId = pContactId;
    }

    public void setContactName(String pContactName) {
        contactName = pContactName;
    }

    public void setEmail(String pEmail) {
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
