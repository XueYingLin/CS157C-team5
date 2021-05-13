package com.springboot.subway.repository;

import com.springboot.subway.model.subway.Agency;
import com.springboot.subway.model.subway.Train;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainRepository extends MongoRepository<Train, String> {
    Train findByCode(String code);

    Train findByCodeAndAgency(String trainCode, Agency agency);
}
