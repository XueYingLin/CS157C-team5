package com.cs157c.subway.repository.user;

import com.cs157c.subway.model.user.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);

}
