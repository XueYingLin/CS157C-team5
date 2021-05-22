package com.cs157c.subway.model.train;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "station")
public class Station {


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
        if (!(o instanceof Station)) return false;
        Station station = (Station) o;
        return Objects.equals(getId(), station.getId()) &&
                Objects.equals(getCode(), station.getCode()) &&
                Objects.equals(getName(), station.getName()) &&
                Objects.equals(getDetail(), station.getDetail());
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
