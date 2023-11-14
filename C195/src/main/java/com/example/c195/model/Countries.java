package com.example.c195.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Countries class
 */

public class Countries {
    private int countryId;
    private String country;
    private Date createDate;
    private String createdBy;
    private Time lastUpdate;
    private String lastUpdatedBy;

    /**
     * Constructor for country class
     * @param pCountryId
     * @param pCountry
     * @param pCreateDate
     * @param pCreatedBy
     * @param pLastUpdate
     * @param pLastUpdatedBy
     */

    public Countries(int pCountryId, String pCountry, Date pCreateDate, String pCreatedBy, Time pLastUpdate,
                     String pLastUpdatedBy) {
        countryId = pCountryId;
        country = pCountry;
        createDate = pCreateDate;
        createdBy = pCreatedBy;
        lastUpdate = pLastUpdate;
        lastUpdatedBy = pLastUpdatedBy;
    }

    /**
     * Setter for countryID
     * @param pCountryId
     */
    public void setCountryId(int pCountryId) {
        countryId = pCountryId;
    }

    /**
     * Setter for Country name
     * @param pCountry
     */
    public void setCountry(String pCountry) {
        country = pCountry;
    }

    /**
     * Setter for the country Create Date
     * @param pCreateDate
     */
    public void setCreateDate(Date pCreateDate) {
        createDate = pCreateDate;
    }

    /**
     * Setter for the person who created the country entry
     * @param pCreatedBy
     */

    public void setCreatedBy(String pCreatedBy) {
        createdBy = pCreatedBy;
    }

    /**
     * Setter for when the last update to countries happened
     * @param pLastUpdate
     */
    public void setLastUpdate(Time pLastUpdate) {
        lastUpdate = pLastUpdate;
    }

    /**
     * Setter for who last update the countries
     * @param pLastUpdatedBy
     */
    public void setLastUpdatedBy(String pLastUpdatedBy) {
        lastUpdatedBy = pLastUpdatedBy;
    }

    /**
     * Getter for country ID
     * @return countryID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Getter for the country name
     * @return country
     */
    public String getCountry() {
        return  country;
    }

    /**
     * getter for the creation date
     * @return createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Getter for who created the country list
     * @return createdBy
     */
    public String getCreatedBy() {
        return  createdBy;
    }

    /**
     * Getter for when the countries were last updated
     * @return lastUpdate
     */
    public Time getLastUpdate() {
        return  lastUpdate;
    }

    /**
     * Getter for when the countries were last updated
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

}
