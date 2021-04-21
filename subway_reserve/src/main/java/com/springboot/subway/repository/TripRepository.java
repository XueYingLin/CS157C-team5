package com.springboot.subway.repository;

import com.springboot.subway.model.subway.Agency;
import com.springboot.subway.model.subway.Train;
import com.springboot.subway.model.subway.Trip;
import com.springboot.subway.model.subway.station;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TripRepository extends MongoRepository<Trip, String> {
    List<Trip> findAllByBeginStopAndEndStop(station begin, station end);

    List<Trip> findByAgency(Agency agency);

    Trip findByBeginStopAndEndStopAndTrain(station begin, station end, Train train);

    /* find by code*/
//    Trip findFirstByAgency_Code(String code);
}
