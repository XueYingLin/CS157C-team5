package com.cs157c.subway.repository.train;

import com.cs157c.subway.model.train.Agency;
import com.cs157c.subway.model.train.Station;
import com.cs157c.subway.model.train.Train;
import com.cs157c.subway.model.train.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TripRepository extends MongoRepository<Trip, String> {

    Trip findByBeginStationAndEndStationAndTrain(Station begin, Station end, Train train);


    List<Trip> findAllByBeginStationAndEndStation(Station begin, Station end);

    List<Trip> findByAgency(Agency agency);
}
