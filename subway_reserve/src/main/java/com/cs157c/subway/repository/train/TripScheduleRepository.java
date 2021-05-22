package com.cs157c.subway.repository.train;

import com.cs157c.subway.model.train.Trip;
import com.cs157c.subway.model.train.TripSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface TripScheduleRepository extends MongoRepository<TripSchedule, String> {
    TripSchedule findByTripDetailAndTripDate(Trip tripDetail, String tripDate);
}