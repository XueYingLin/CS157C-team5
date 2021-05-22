package com.cs157c.subway.repository.train;

import com.cs157c.subway.model.train.Station;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StationRepository extends MongoRepository<Station, String> {
    Station findByCode(String code);
}
