package com.cs157c.subway.dto.model.train;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;


@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketDto {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }

    public String getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(String journeyDate) {
        this.journeyDate = journeyDate;
    }

    public String getSourceStop() {
        return sourceStop;
    }

    public void setSourceStop(String sourceStop) {
        this.sourceStop = sourceStop;
    }

    public String getDestinationStop() {
        return destinationStop;
    }

    public void setDestinationStop(String destinationStop) {
        this.destinationStop = destinationStop;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerMobileNumber() {
        return passengerMobileNumber;
    }

    public void setPassengerMobileNumber(String passengerMobileNumber) {
        this.passengerMobileNumber = passengerMobileNumber;
    }














    private String id;

    private String trainCode;

    private int seatNumber;

    private boolean cancellable;

    private String journeyDate;

    private String sourceStop;

    private String destinationStop;

    private String passengerName;

    private String passengerMobileNumber;
}
