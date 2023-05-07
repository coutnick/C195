package com.c195.c195nrains.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Users {
    private final int userId;
    private final String userName;
    private final String text;
    private final LocalDateTime createDate;
    private final String createdBy;
    private final Timestamp lastUpdate;
    private final String lastUpdatedBy;

    public Users(int pUserId, String pUserName, String pText, LocalDateTime pCreateDate, String pCreatedBy,
                 Timestamp pLastUpdate, String pLastUpdateBy) {
        userId = pUserId;
        userName = pUserName;
        text = pText;
        createDate = pCreateDate;
        createdBy = pCreatedBy;
        lastUpdate = pLastUpdate;
        lastUpdatedBy = pLastUpdateBy;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
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
}
