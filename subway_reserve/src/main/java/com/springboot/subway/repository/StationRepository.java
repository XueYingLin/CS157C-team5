package com.springboot.subway.repository;

import com.springboot.subway.model.subway.station;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StationRepository extends MongoRepository<station, String> {
    station findByCode(String code);
}
