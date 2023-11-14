package com.example.c195.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Customer class
 */
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

    /**
     * Constructor for the Customers object
     * @param pCustomerId int
     * @param pCustomerName String
     * @param pAddress String
     * @param pPostalCode String
     * @param pPhone String
     * @param pCreateDate LocalDateTime
     * @param pCreatedBy String
     * @param pLastUpdate Timestamp
     * @param pLastUpdatedBy String
     * @param pDivisionId int
     */
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

    /**
     * Setter for the customer id
     * @param pCustomerId int
     */
    public void setCustomerId(int pCustomerId) {
        customerId = pCustomerId;
    }

    /**
     * Setter for the customers Name in the customer Object
     * @param pCustomerName String
     */
    public void setCustomerName(String pCustomerName) {
        customerName = pCustomerName;
    }

    /**
     * Setter for the address of the customer in the Customer object
     * @param pAddress String
     */
    public void setAddress(String pAddress) {
        address = pAddress;
    }

    /**
     * Setter for the postal code of the customer in the Customer Object
     * @param pPostalCode String
     */
    public void setPostalCode(String pPostalCode) {
       postalCode = pPostalCode;
    }

    /**
     * Setter for the phone number of the customer in the customer Object
     * @param pPhone String
     */
    public void setPhone(String pPhone) {
        phone = pPhone;
    }

    /**
     * Setter for the creation date of a customer Object
     * @param pCreateDate LocalDateTime
     */
    public void setCreateDate(LocalDateTime pCreateDate) {
       createDate = pCreateDate;
    }

    /**
     * Setter for the person that created the Customer object
     * @param pCreatedBy String
     */
    public void setCreatedBy(String pCreatedBy) {
        createdBy = pCreatedBy;
    }

    /**
     * Setter for the time of the last customer object's  update
     * @param pLastUpdate Timestamp
     */
    public void setLastUpdate(Timestamp pLastUpdate) {
        lastUpdate = pLastUpdate;
    }

    /**
     * Setter for who last updated the Customer object
     * @param pLastUpdatedBy String
     */
    public void setLastUpdatedBy(String pLastUpdatedBy) {
        lastUpdatedBy = pLastUpdatedBy;
    }

    /**
     * Setter for the division id of a Customer object
     * @param pDivisionId int
     */
    public void setDivisionId(int pDivisionId) {
        divisionId = pDivisionId;
    }

    /**
     * Setter for the customerId of the Customer object.
     * @return customerId
     */
    public int getCustomerId() { return customerId;}

    /**
     * Getter for the customer name of the Customer object
     * @return customerName
     */
    public String getCustomerName() {return customerName;}

    /**
     * Getter for the address of the customer object
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter for the postal code of the Customer object
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Getter for the phone number of the Customer object
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Getter for the creation date of the Customer object
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Getter for the person that created the Customer object
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Getter for the time that the Customer object was updated
     * @return lastUpdate
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Getter for the person that last updated the Customer object
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Getter for the divisionId of the customer object
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }
}
