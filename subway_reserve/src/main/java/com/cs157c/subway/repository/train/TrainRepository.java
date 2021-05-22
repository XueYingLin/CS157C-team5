package com.cs157c.subway.repository.train;

import com.cs157c.subway.model.train.Agency;
import com.cs157c.subway.model.train.Train;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TrainRepository extends MongoRepository<Train, String> {
    Train findByCode(String busCode);

    Train findByCodeAndAgency(String busCode, Agency agency);
}
