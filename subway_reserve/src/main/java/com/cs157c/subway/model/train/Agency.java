package com.cs157c.subway.model.train;

import com.cs157c.subway.model.user.User;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;



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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Train> getTrains() {
        return trains;
    }

    public void setTrains(Set<Train> trains) {
        this.trains = trains;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOfficeHour() {
        return officeHour;
    }

    public void setOfficeHour(String officeHour) {
        this.officeHour = officeHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agency)) return false;
        Agency agency = (Agency) o;
        return Objects.equals(getId(), agency.getId()) &&
                Objects.equals(getCode(), agency.getCode()) &&
                Objects.equals(getName(), agency.getName()) &&
                Objects.equals(getDetails(), agency.getDetails()) &&
                Objects.equals(getContactInfo(), agency.getContactInfo()) &&
                Objects.equals(getAddress(), agency.getAddress()) &&
                Objects.equals(getOfficeHour(), agency.getOfficeHour()) &&
                Objects.equals(getOwner(), agency.getOwner()) &&
                Objects.equals(getTrains(), agency.getTrains());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getName(), getDetails(), getContactInfo(), getAddress(), getOfficeHour(), getOwner(), getTrains());
    }







    /* private fields */

    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String code;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String name;

    private String details;


    private String contactInfo;

    private String address;

    private String officeHour;


    @DBRef(lazy = true)
    private User owner;

    @DBRef(lazy = true)
    private Set<Train> trains;
}
