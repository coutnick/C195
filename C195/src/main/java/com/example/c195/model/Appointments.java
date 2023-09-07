package com.example.c195.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    public void setAppointmentId(int pAppointmentId) {
        appointmentId = pAppointmentId;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }

    public void setLocation(String pLocation) {
        location = pLocation;
    }

    public void setType(String pType) {
        type = pType;
    }

    public void setStart(LocalDateTime pStart) {
        start = pStart;
    }

    public void setEnd(LocalDateTime pEnd) {
        end = pEnd;
    }

    public void setCreateDate(LocalDateTime pCreateDate) {
        createDate = pCreateDate;
    }

    public void setCreatedBy(String pCreatedBy) {
        createdBy = pCreatedBy;
    }

    public void setLastUpdate(Timestamp pLastUpdate) {
        lastUpdate = pLastUpdate;
    }

    public void setLastUpdatedBy(String pLastUpdatedBy) {
        lastUpdatedBy = pLastUpdatedBy;
    }

    public void setCustomerId(int pCustomerId) {
        customerId = pCustomerId;
    }

    public void setUserId(int pUserId) {
        userId = pUserId;
    }

    public void setContactId(int pContactId) {
        contactId = pContactId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getUserId() {
        return userId;
    }

    public int getContactId() {
        return contactId;
    }
}
