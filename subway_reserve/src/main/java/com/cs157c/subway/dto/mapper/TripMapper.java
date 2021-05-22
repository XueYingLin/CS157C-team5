package com.cs157c.subway.dto.mapper;

import com.cs157c.subway.dto.model.train.TripDto;
import com.cs157c.subway.model.train.Trip;


public class TripMapper {
    public static TripDto toTripDto(Trip trip) {

        TripDto tdo = new TripDto();


        tdo.setId(trip.getId());
        tdo.setAgencyCode(trip.getAgency().getCode());
        tdo.setBeginStationCode(trip.getBeginStation().getCode());
        tdo.setBeginStationName(trip.getBeginStation().getName());
        tdo.setEndStationCode(trip.getEndStation().getCode());
        tdo.setEndStationName(trip.getEndStation().getName());
        tdo.setTrainCode(trip.getTrain().getCode());
        tdo.setJourneyTime(trip.getJourneyTime());
        tdo.setFare(trip.getFare());


        return tdo;
    }
}
