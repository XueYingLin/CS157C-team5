package com.cs157c.subway.model.train;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "trip")
public class Trip {


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

    public Station getBeginStation() {
        return beginStation;
    }

    public void setBeginStation(Station beginStation) {
        this.beginStation = beginStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
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
                Objects.equals(getId(), trip.getId()) &&
                Objects.equals(getBeginStation(), trip.getBeginStation()) &&
                Objects.equals(getEndStation(), trip.getEndStation()) &&
                Objects.equals(getTrain(), trip.getTrain()) &&
                Objects.equals(getAgency(), trip.getAgency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFare(), getJourneyTime(), getBeginStation(), getEndStation(), getTrain(), getAgency());
    }














    /* private fields */
    @Id
    private String id;

    private int fare;

    private int journeyTime;

    @DBRef
    private Station beginStation;

    @DBRef
    private Station endStation;

    @DBRef
    private Train train;

    @DBRef
    private Agency agency;

}
