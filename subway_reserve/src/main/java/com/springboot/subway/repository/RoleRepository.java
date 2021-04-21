package com.springboot.subway.repository;

import com.springboot.subway.model.user.role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<role, String> {
    role findByRole(String role);
}
