package com.example.c195.model;

import java.sql.Date;
import java.sql.Time;

public class Users {
    private int userId;
    private String userName;
    private String password;
    private Date createDate;
    private String createdBy;
    private Time lastUpdate;
    private String lastUpdatedBy;

    public Users(int pUserId, String pUserName, String pPassword, Date pCreateDate, String pCreatedBy,
                 Time pLastUpdate, String pLastUpdatedBy) {
        userId = pUserId;
        userName = pUserName;
        password = pPassword;
        createDate = pCreateDate;
        createdBy = pCreatedBy;
        lastUpdate = pLastUpdate;
        lastUpdatedBy = pLastUpdatedBy;
    }

    public void setUserId(int pUserId) {
        userId = pUserId;
    }

    public void setUserName(String pUserName) {
        userName = pUserName;
    }

    public void setPassword(String pPassword) {
        password = pPassword;
    }

    public void setCreateDate(Date pCreateDate) {
        createDate = pCreateDate;
    }

    public void setCreatedBy(String pCreatedBy) {
        createdBy = pCreatedBy;
    }

    public void setLastUpdate(Time pLastUpdate) {
        lastUpdate = pLastUpdate;
    }

    public void setLastUpdatedBy(String pLastUpdatedBy) {
        lastUpdatedBy = pLastUpdatedBy;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Time getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
}
