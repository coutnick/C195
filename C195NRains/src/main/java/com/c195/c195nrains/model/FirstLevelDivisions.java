package com.c195.c195nrains.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FirstLevelDivisions {

    private final int divisionId;
    private final String division;
    private final LocalDateTime createDate;
    private final String createdBy;
    private final Timestamp lastUpdate;
    private final String lastUpdatedBy;
    private final int countryId;

    public FirstLevelDivisions(int pDivisionId, String pDivision, LocalDateTime pCreateDate, String pCreatedBy,
                               Timestamp pLastUpdate, String pLastUpdatedBy, int pCountryId) {
        divisionId = pDivisionId;
        division = pDivision;
        createDate = pCreateDate;
        createdBy = pCreatedBy;
        lastUpdate = pLastUpdate;
        lastUpdatedBy = pLastUpdatedBy;
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
