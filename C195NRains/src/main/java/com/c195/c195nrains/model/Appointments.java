package com.c195.c195nrains.model;

import java.nio.channels.ClosedChannelException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointments {
    private final int appointmentID;
    private final String title;
    private final String description;
    private final String type;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final LocalDateTime createDate;
    private final String createdBy;
    private Timestamp lastUpdate;
    private final String lastUpdatedBy;
    private final int customerId;
    private final int userId;
    private final int contactId;

    public Appointments(int pAppointmentId, String pTitle, String pDescription, String pType, LocalDateTime pStart,
                        LocalDateTime pEnd, LocalDateTime pCreatedDate, String pCreatedBy, Timestamp pLastUpdate,
                        String pLastUpdatedBy, int pCustomerId, int pUserId, int pContactId) {
        appointmentID = pAppointmentId;
        title = pTitle;
        description = pDescription;
        type = pType;
        start = pStart;
        end = pEnd;
        createDate = pCreatedDate;
        createdBy = pCreatedBy;
        lastUpdate = pLastUpdate;
        lastUpdatedBy = pLastUpdatedBy;
        customerId = pCustomerId;
        userId = pUserId;
        contactId = pContactId;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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
