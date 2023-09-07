package com.example.c195.model;

import java.sql.Date;
import java.sql.Time;

public class Countries {
    private int countryId;
    private String country;
    private Date createDate;
    private String createdBy;
    private Time lastUpdate;
    private String lastUpdatedBy;

    public Countries(int pCountryId, String pCountry, Date pCreateDate, String pCreatedBy, Time pLastUpdate,
                     String pLastUpdatedBy) {
        countryId = pCountryId;
        country = pCountry;
        createDate = pCreateDate;
        createdBy = pCreatedBy;
        lastUpdate = pLastUpdate;
        lastUpdatedBy = pLastUpdatedBy;
    }

    public void setCountryId(int pCountryId) {
        countryId = pCountryId;
    }

    public void setCountry(String pCountry) {
        country = pCountry;
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

    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return  country;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return  createdBy;
    }

    public Time getLastUpdate() {
        return  lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

}
