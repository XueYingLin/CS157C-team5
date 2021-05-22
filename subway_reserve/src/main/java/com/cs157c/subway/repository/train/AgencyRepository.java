package com.cs157c.subway.repository.train;

import com.cs157c.subway.model.train.Agency;
import com.cs157c.subway.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AgencyRepository extends MongoRepository<Agency, String> {
    Agency findByCode(String agencyCode);

    Agency findByOwner(User owner);

    Agency findByName(String name);
}
