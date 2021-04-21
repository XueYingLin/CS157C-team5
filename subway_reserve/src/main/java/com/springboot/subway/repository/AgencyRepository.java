package com.springboot.subway.repository;

import com.springboot.subway.model.subway.Agency;
import com.springboot.subway.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgencyRepository extends MongoRepository<Agency, String> {
    Agency findByName(String name);

    Agency findByCode(String code);

    Agency findByUser(User user);
}
