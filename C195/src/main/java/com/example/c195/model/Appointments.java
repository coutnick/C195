package com.example.c195.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Appointments class
 */

public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;

    /**
     * Constructer for the appointment class. Creates an appointment
     *
     * @param pAppointmentId
     * @param pTitle
     * @param pDescription
     * @param pLocation
     * @param pType
     * @param pStart
     * @param pEnd
     * @param pCreateDate
     * @param pCreatedBy
     * @param pLastUpdate
     * @param pLastUpdatedBy
     * @param pCustomerId
     * @param pUserId
     * @param pContactId
     *
     */
    public Appointments(int pAppointmentId, String pTitle, String pDescription, String pLocation,
                        String pType, LocalDateTime pStart, LocalDateTime pEnd, LocalDateTime pCreateDate, String pCreatedBy,
                        Timestamp pLastUpdate, String pLastUpdatedBy, int pCustomerId, int pUserId, int pContactId) {
        appointmentId = pAppointmentId;
        title = pTitle;
        description = pDescription;
        location = pLocation;
        type = pType;
        start = pStart;
        end = pEnd;
        createDate = pCreateDate;
        createdBy = pCreatedBy;
        lastUpdate = pLastUpdate;
        lastUpdatedBy = pLastUpdatedBy;
        customerId = pCustomerId;
        userId = pUserId;
        contactId = pContactId;
    }

    /**
     * Setter for the appointment ID.
     * @param pAppointmentId
     */

    public void setAppointmentId(int pAppointmentId) {
        appointmentId = pAppointmentId;
    }

    /**
     * Setter for appointment title.
     * @param pTitle
     */

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    /**
     * setter for appointment description
     * @param pDescription
     */

    public void setDescription(String pDescription) {
        description = pDescription;
    }

    /**
     * setter for appointment Location
     * @param pLocation
     */

    public void setLocation(String pLocation) {
        location = pLocation;
    }

    /**
     * setter for appointment type
     * @param pType
     */

    public void setType(String pType) {
        type = pType;
    }

    /**
     * setter for appointment start time
     * @param pStart
     */

    public void setStart(LocalDateTime pStart) {
        start = pStart;
    }

    /**
     * setter for appointment end time
     * @param pEnd
     */

    public void setEnd(LocalDateTime pEnd) {
        end = pEnd;
    }

    /**
     * setter for appointments creation date
     * @param pCreateDate
     */

    public void setCreateDate(LocalDateTime pCreateDate) {
        createDate = pCreateDate;
    }

    /**
     * setter for the appointments creator
     * @param pCreatedBy
     */

    public void setCreatedBy(String pCreatedBy) {
        createdBy = pCreatedBy;
    }

    /**
     * setter for the appointments last update time.
     * @param pLastUpdate
     */

    public void setLastUpdate(Timestamp pLastUpdate) {
        lastUpdate = pLastUpdate;
    }

    /**
     * setter for who last updated the appointment
     * @param pLastUpdatedBy
     */

    public void setLastUpdatedBy(String pLastUpdatedBy) {
        lastUpdatedBy = pLastUpdatedBy;
    }

    /**
     * setter for the customer ID.
     * @param pCustomerId
     */

    public void setCustomerId(int pCustomerId) {
        customerId = pCustomerId;
    }

    /**
     * setter for userID
     * @param pUserId
     */

    public void setUserId(int pUserId) {
        userId = pUserId;
    }

    /**
     * setter for contact ID
     * @param pContactId
     */

    public void setContactId(int pContactId) {
        contactId = pContactId;
    }

    /**
     * getter for appointment ID
     * @return appointmentID
     */

    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * getter for appointment title
     * @return title
     */

    public String getTitle() {
        return title;
    }

    /**
     * getter for the appointments description
     * @return description
     */

    public String getDescription() {
        return description;
    }

    /**
     * getter for the appointments location
     * @return location
     */

    public String getLocation() {
        return location;
    }

    /**
     * getter for the appointment type
     * @return type
     */

    public String getType() {
        return type;
    }

    /**
     * getter for the appointments start time
     * @return start
     */

    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Getter for the appointments end date
     * @return end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Getter for the appointments creation date
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Getter for appointments creator
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * getter for the time of the appointments last update
     * @return lastUpdate
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Getter for the person who last updated the appointment
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Getter for appointments customerID
     * @return customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Getter for appointments userId
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Getter for the appointments contacts ID
     * @return contactID
     */
    public int getContactId() {
        return contactId;
    }
}
