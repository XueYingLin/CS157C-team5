package com.springboot.subway.model.subway;

import java.util.Objects;
import javax.persistence.Id;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder(toBuilder = true)
@Document(collection = "trip")
public class Trip {

    /* Accessors And Mutator */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public station getBeginStop() {
        return beginStop;
    }

    public void setBeginStop(station beginStop) {
        this.beginStop = beginStop;
    }

    public station getEndStop() {
        return endStop;
    }

    public void setEndStop(station endStop) {
        this.endStop = endStop;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;
        Trip trip = (Trip) o;
        return getFare() == trip.getFare() &&
                getJourneyTime() == trip.getJourneyTime() &&
                getId().equals(trip.getId()) &&
                getBeginStop().equals(trip.getBeginStop()) &&
                getEndStop().equals(trip.getEndStop()) &&
                getTrain().equals(trip.getTrain()) &&
                getAgency().equals(trip.getAgency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFare(), getJourneyTime(), getBeginStop(), getEndStop(), getTrain(), getAgency());
    }


    /* private fields */
    @Id
    private String id;

    private int fare;

    private int journeyTime;

    @DBRef
    private station beginStop;

    @DBRef
    private station endStop;

    @DBRef
    private Train train;

    @DBRef
    private Agency agency;
}
