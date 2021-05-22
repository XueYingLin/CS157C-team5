package com.cs157c.subway.repository.user;

import com.cs157c.subway.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

}
