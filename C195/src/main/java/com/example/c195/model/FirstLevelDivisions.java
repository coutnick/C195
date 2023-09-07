package com.example.c195.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FirstLevelDivisions {

    private int divisionId;
    private String division;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int countryId;


    public FirstLevelDivisions(int pDivisionId, String pDivision, LocalDateTime pCreateDate, String pCreatedBy, Timestamp pLastUpdate,
                               String pLastUpdatedBy, int pCountryId) {
        divisionId = pDivisionId;
        division = pDivision;
        createDate = pCreateDate;
        createdBy = pCreatedBy;
        lastUpdate = pLastUpdate;
        lastUpdatedBy = pLastUpdatedBy;
        countryId = pCountryId;
    }

    public void setDivisionId(int pDivisionId) {
        divisionId = pDivisionId;
    }

    public void setDivision(String pDivision) {
        division = pDivision;
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

    public void setCountryId(int pCountryId) {
        countryId = pCountryId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getDivision() {
        return division;
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

    public int getCountryId() {
        return countryId;
    }
}
