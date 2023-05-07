package com.c195.c195nrains.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Customers {
    private final int customerId;
    private final String customerName;
    private final String address;
    private final String postalCode;
    private final String phone;
    private final LocalDateTime createDate;
    private final String createBy;
    private final LocalDateTime lastUpdate;
    private final String lastUpdatedBy;
    private final int divisionId;

    public Customers (int pCustomerId, String pCustomerName, String pAddress, String pPostalCode, String pPhone,
                      LocalDateTime pCreateDate, String pCreateBy, LocalDateTime pLastUpdate, String pLastUpdatedBy,
                      int pDivisionId) {
        customerId = pCustomerId;
        customerName = pCustomerName;
        address = pAddress;
        postalCode = pPostalCode;
        phone = pPhone;
        createDate = pCreateDate;
        createBy = pCreateBy;
        lastUpdate = pLastUpdate;
        lastUpdatedBy = pLastUpdatedBy;
        divisionId = pDivisionId;
    }

    public int getCustomerId() {
        return  customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

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

    public String getCreateBy() {
        return createBy;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public int getDivisionId() {
        return divisionId;
    }

}
