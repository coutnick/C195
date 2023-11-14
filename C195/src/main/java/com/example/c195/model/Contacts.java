package com.example.c195.model;

/**
 * Contact class
 */
public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    /**
     * Contact Constructor
     * @param pContactId
     * @param pContactName
     * @param pEmail
     */
    public Contacts(int pContactId, String pContactName, String pEmail) {
        contactId = pContactId;
        contactName = pContactName;
        email = pEmail;
    }

    /**
     * Setter for contact ID
     * @param pContactId
     */

    public void setContactId(int pContactId) {
        contactId = pContactId;
    }

    /**
     * Setter for contacts Name
     * @param pContactName
     */
    public void setContactName(String pContactName) {
        contactName = pContactName;
    }

    /**
     * Setter for Contacts Email
     * @param pEmail
     */
    public void setEmail(String pEmail) {
        email = pEmail;
    }

    /**
     * Getter for contact ID
     * @return contact ID
     */

    public int getContactId() {
        return contactId;
    }

    /**
     * getter for contacts name
     * @return contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Getter for contacts Email
     * @return email
     */

    public String getEmail() {
        return email;
    }
}
