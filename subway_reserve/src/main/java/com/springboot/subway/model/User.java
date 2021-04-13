package com.springboot.subway.model;

import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Set;

@Accessors(chain = true)
@Document(collection = "user")
public class User {
    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;
    private String password;
    private String first_Name;
    private String last_Name;
    private String mobile_Number;

    @DBRef
    private Set<Role> roles;

    public User() {
    }

    public User(String id,
                String email,
                String password,
                String first_Name,
                String last_Name,
                String mobile_Number,
                Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.mobile_Number = mobile_Number;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getMobileNumber() {
        return mobile_Number;
    }

    public void setMobileNumber(String mobile_Number) {
        this.mobile_Number = mobile_Number;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first_Name='" + first_Name + '\'' +
                ", last_Name='" + last_Name + '\'' +
                ", mobileNumber='" + mobile_Number + '\'' +
                ", roles=" + roles +
                '}';
    }
}
