package com.example.c195.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Customers {


    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;

    public Customers(int pCustomerId, String pCustomerName, String pAddress, String pPostalCode, String pPhone,
                     LocalDateTime pCreateDate, String pCreatedBy, Timestamp pLastUpdate, String pLastUpdatedBy, int pDivisionId) {
        customerId = pCustomerId;
        customerName = pCustomerName;
        address = pAddress;
        postalCode = pPostalCode;
        phone = pPhone;
        createDate = pCreateDate;
        createdBy = pCreatedBy;
        lastUpdate = pLastUpdate;
        lastUpdatedBy = pLastUpdatedBy;
        divisionId = pDivisionId;
    }

    public void setCustomerId(int pCustomerId) {
        customerId = pCustomerId;
    }

    public void setCustomerName(String pCustomerName) {
        customerName = pCustomerName;
    }

    public void setAddress(String pAddress) {
        address = pAddress;
    }

    public void setPostalCode(String pPostalCode) {
       postalCode = pPostalCode;
    }

    public void setPhone(String pPhone) {
        phone = pPhone;
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

    public void setDivisionId(int pDivisionId) {
        divisionId = pDivisionId;
    }

    public int getCustomerId() { return customerId;}

    public String getCustomerName() {return customerName;}

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
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

    public int getDivisionId() {
        return divisionId;
    }
}
