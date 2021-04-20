package com.springboot.subway.model.user;

import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Objects;

@Accessors(chain = true)
@Document(collection = "role")
public class Role {
    @Id
    private String id;

    public Role() {
    }

    public Role(String id, String role) {
        this.id = id;
        this.role = role;
    }

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role1 = (Role) o;
        return getId().equals(role1.getId()) && getRole().equals(role1.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole());
    }

    public void setRole(String role) {
        this.role = role;
    }
}

