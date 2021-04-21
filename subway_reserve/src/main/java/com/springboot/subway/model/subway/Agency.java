package com.springboot.subway.model.subway;

import com.springboot.subway.model.user.User;
import lombok.Builder;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Objects;
import java.util.Set;

@Builder(toBuilder = true)
@Document(collection = "agency")
public class Agency {

    /* Accessors And Mutator */

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Train> getTrains() {
        return trains;
    }

    public void setTrains(Set<Train> trains) {
        this.trains = trains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof com.springboot.subway.model.subway.Agency)) return false;
        com.springboot.subway.model.subway.Agency agency = (com.springboot.subway.model.subway.Agency) o;
        return getId().equals(agency.getId()) &&
                getCode().equals(agency.getCode()) &&
                getName().equals(agency.getName()) &&
                getDetails().equals(agency.getDetails()) &&
                getUser().equals(agency.getUser()) &&
                getTrains().equals(agency.getTrains());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getName(), getDetails(), getUser(), getTrains());
    }

    /* private fields */
    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String code;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String name;

    private String details;

    @DBRef(lazy = true)
    private User user;

    @DBRef(lazy = true)
    private Set<Train> trains;
}
