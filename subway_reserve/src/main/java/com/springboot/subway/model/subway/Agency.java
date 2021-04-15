package com.springboot.subway.model.subway;

import com.springboot.subway.model.user.User;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Objects;
import java.util.Set;

@Accessors(chain = true)
@Document(collection = "agency")
public class Agency {
    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String code;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String name;

    private String details;

    @DBRef(lazy = true)
    private User owner;

    @DBRef(lazy = true)
    private Set<Subway> subways;

    public Agency() {
    }

    public Agency(String id, String code, String name, String details, User owner, Set<Subway> subways) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.details = details;
        this.owner = owner;
        this.subways = subways;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Subway> getSubways() {
        return subways;
    }

    public void getSubways(Set<Subway> subways) {
        this.subways = subways;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agency)) return false;
        Agency agency = (Agency) o;
        return getId().equals(agency.getId()) && Objects.equals(getCode(), agency.getCode()) && Objects.equals(getName(), agency.getName()) && Objects.equals(getDetails(), agency.getDetails()) && Objects.equals(getOwner(), agency.getOwner()) && Objects.equals(getSubways(), agency.getSubways());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getName(), getDetails(), getOwner(), getSubways());
    }
}
