package com.springboot.subway.model.subway;

import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Accessors(chain = true)
@Document(collection = "subway")
public class Subway {
    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String code;

    private int capacity;

    private String make;

    @DBRef(lazy = true)
    private Agency agency;

    public Subway() {
    }

    public Subway(String id) {
        this.id = id;
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
        if (!(o instanceof Subway)) return false;
        Subway subway = (Subway) o;
        return getCapacity() == subway.getCapacity() && getId().equals(subway.getId()) && Objects.equals(getCode(), subway.getCode()) && Objects.equals(getMake(), subway.getMake()) && Objects.equals(getAgency(), subway.getAgency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getCapacity(), getMake(), getAgency());
    }
}
