package com.springboot.subway.DTO.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripDto {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeginStationCode() {
        return beginStationCode;
    }

    public void setBeginStationCode(String beginStationCode) {
        this.beginStationCode = beginStationCode;
    }

    public String getEndStationCode() {
        return endStationCode;
    }

    public void setEndStationCode(String endStationCode) {
        this.endStationCode = endStationCode;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public int getJourneyTime() {
        return journeyTime;
    }

    public void setJourneyTime(int journeyTime) {
        this.journeyTime = journeyTime;
    }

    public String getBeginStationName() {
        return beginStationName;
    }

    public void setBeginStationName(String beginStationName) {
        this.beginStationName = beginStationName;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }



    private String id;

    private String beginStationCode;

    private String endStationCode;

    private int fare;

    private int journeyTime;

    private String beginStationName;

    private String endStationName;

    private String trainCode;

    private String agencyCode;
}
