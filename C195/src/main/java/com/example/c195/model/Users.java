package com.example.c195.model;

import java.sql.Date;
import java.sql.Time;

/**
 * User class
 */
public class Users {
    private int userId;
    private String userName;
    private String password;
    private Date createDate;
    private String createdBy;
    private Time lastUpdate;
    private String lastUpdatedBy;

    /**
     * Constructor for the user class
     * @param pUserId int
     * @param pUserName String
     * @param pPassword String
     * @param pCreateDate Date
     * @param pCreatedBy String
     * @param pLastUpdate Time
     * @param pLastUpdatedBy String
     */
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

    /**
     * Setter for the userId
     * @param pUserId int
     */
    public void setUserId(int pUserId) {
        userId = pUserId;
    }

    /**
     * setter for the username
     * @param pUserName String
     */
    public void setUserName(String pUserName) {
        userName = pUserName;
    }

    /**
     * Setter for the password
     * @param pPassword String
     */
    public void setPassword(String pPassword) {
        password = pPassword;
    }

    /**
     * Setter for the User creation date
     * @param pCreateDate Date
     */
    public void setCreateDate(Date pCreateDate) {
        createDate = pCreateDate;
    }

    /**
     * Setter for the user that created another User
     * @param pCreatedBy String
     */
    public void setCreatedBy(String pCreatedBy) {
        createdBy = pCreatedBy;
    }

    /**
     * Setter for the time the last User was updated
     * @param pLastUpdate Time
     */
    public void setLastUpdate(Time pLastUpdate) {
        lastUpdate = pLastUpdate;
    }

    /**
     * Setter for the user that last updated another User
     * @param pLastUpdatedBy String
     */
    public void setLastUpdatedBy(String pLastUpdatedBy) {
        lastUpdatedBy = pLastUpdatedBy;
    }

    /**
     * Getter for the userID
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Getter for the userName
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter for the users password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for the creation date of a user
     * @return createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Getter for the user that created another User
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Getter for the time that the last user was updated
     * @return lastUpdate
     */
    public Time getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Getter for the user that last updated another User
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
}
