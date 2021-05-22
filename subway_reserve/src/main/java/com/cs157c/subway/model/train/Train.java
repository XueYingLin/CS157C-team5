package com.cs157c.subway.model.train;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "train")
public class Train {


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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }








    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Train)) return false;
        Train train = (Train) o;
        return getCapacity() == train.getCapacity() &&
                Objects.equals(getId(), train.getId()) &&
                Objects.equals(getCode(), train.getCode()) &&
                Objects.equals(getMake(), train.getMake()) &&
                Objects.equals(getAgency(), train.getAgency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getCapacity(), getMake(), getAgency());
    }















    /* private fields */
    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String code;

    private int capacity;

    private String make;

    @DBRef(lazy = true)
    private Agency agency;
}
