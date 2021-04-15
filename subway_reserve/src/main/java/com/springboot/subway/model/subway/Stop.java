package com.springboot.subway.model.subway;

import com.springboot.subway.model.user.User;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

public class Stop {
    @Accessors(chain = true)
    @Document(collection = "ticket")
    public class Ticket {
        @Id
        private String id;

        private int seatNumber;

        private boolean cancellable;

        private String journeyDate;

        @DBRef
        private TripSchedule tripSchedule;

        @DBRef
        private User passenger;

        public Ticket(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public TripSchedule getTripSchedule() {
            return tripSchedule;
        }

        public void setTripSchedule(TripSchedule tripSchedule) {
            this.tripSchedule = tripSchedule;
        }

        public User getPassenger() {
            return passenger;
        }

        public void setPassenger(User passenger) {
            this.passenger = passenger;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Ticket)) return false;
            Ticket ticket = (Ticket) o;
            return getSeatNumber() == ticket.getSeatNumber() && isCancellable() == ticket.isCancellable() && getId().equals(ticket.getId()) && Objects.equals(getJourneyDate(), ticket.getJourneyDate()) && Objects.equals(getTripSchedule(), ticket.getTripSchedule()) && Objects.equals(getPassenger(), ticket.getPassenger());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getSeatNumber(), isCancellable(), getJourneyDate(), getTripSchedule(), getPassenger());
        }
    }
}
