package com.c195.c195nrains.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Countries {
    private final int countryId;
    private final String country;
    private final LocalDateTime createDate;
    private final String createdBy;
    private final Timestamp lastUpdate;
    private final String lastUpdatedBy;

    public Countries(int pCountryId, String pCountry, LocalDateTime pCreateDate, String pCreatedBy,
                     Timestamp pLastUpdate, String pLastUpdatedBy) {
        countryId = pCountryId;
        country = pCountry;
        createDate = pCreateDate;
        createdBy = pCreatedBy;
        lastUpdate = pLastUpdate;
        lastUpdatedBy = pLastUpdatedBy;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
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
