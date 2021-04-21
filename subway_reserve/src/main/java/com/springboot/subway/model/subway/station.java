package com.springboot.subway.model.subway;

import java.util.Objects;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder(toBuilder = true)
@Document(collection = "station")
public class station {

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof station)) return false;
        station stop = (station) o;
        return getId().equals(stop.getId()) &&
                getCode().equals(stop.getCode()) &&
                getName().equals(stop.getName()) &&
                getDetail().equals(stop.getDetail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getName(), getDetail());
    }


    /* private fields */
    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String code;

    private String name;

    private String detail;
}
