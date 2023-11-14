package com.example.c195.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * First Level Division Class
 *
 */
public class FirstLevelDivisions {

    private int divisionId;
    private String division;
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int countryId;

    /**
     * Constructor for the FirstLevelDivisions Object
     *
     * @param pDivisionId int
     * @param pDivision string
     * @param pCreateDate LocalDateTime
     * @param pCreatedBy string
     * @param pLastUpdate Timestamp
     * @param pLastUpdatedBy String
     * @param pCountryId int
     */
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

    /**
     * Setter for the divisionId
     * @param pDivisionId int
     */
    public void setDivisionId(int pDivisionId) {
        divisionId = pDivisionId;
    }

    /**
     * Setter for the division
     * @param pDivision String
     */
    public void setDivision(String pDivision) {
        division = pDivision;
    }

    /**
     * setter for the FirstLevelDivisions creation date
     * @param pCreateDate LocalDateTime
     */
    public void setCreateDate(LocalDateTime pCreateDate) {
        createDate = pCreateDate;
    }

    /**
     * setter for the creator of the FirstLevelDivision
     * @param pCreatedBy String
     */
    public void setCreatedBy(String pCreatedBy) {
        createdBy = pCreatedBy;
    }

    /**
     * Setter for the FirstLevelDivisions last update time
     * @param pLastUpdate Timestamp
     */
    public void setLastUpdate(Timestamp pLastUpdate) {
        lastUpdate = pLastUpdate;
    }

    /**
     * Setter for the last user that updated FirstLevelDivisions
     * @param pLastUpdatedBy String
     */
    public void setLastUpdatedBy(String pLastUpdatedBy) {
        lastUpdatedBy = pLastUpdatedBy;
    }

    /**
     * Setter for the FirstLevelDivisions country ID
     * @param pCountryId int
     */
    public void setCountryId(int pCountryId) {
        countryId = pCountryId;
    }

    /**
     * Getter for the FirstLevelDivisions division ID
     * @return divisionID
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Getter for the FirstLevelDivisions division
     * @return division
     */
    public String getDivision() {
        return division;
    }

    /**
     * Getter for the FirstLevelDivisions creations date
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Getter for the user that created the FirstLevelDivisions
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Getter for the time the last FirstLevelDivisions was updated
     * @return lastUpdate
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Getter for the user that lastUpdated a FirstLevelDivision
     * @return lastUpdateBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Getter for FirstLevelDivision countryID
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }
}
