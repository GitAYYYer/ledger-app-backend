package com.ledger.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ledger.model.UserEntity;

import javax.validation.constraints.NotNull;

/*
This is the JSON Payload class, UserEntity is the JPA Entity.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {

    @NotNull
    @JsonProperty("username")
    private String userid;

    @NotNull
    @JsonProperty("password")
    private String password;

    @NotNull
    @JsonProperty("first_name")
    private String firstName;

    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    @NotNull
    @JsonProperty("role")
    private String role;

    public UserModel() {
    }

    public UserModel(String userId) {
        this.userid = userId;
        this.password = null;
        this.lastName = null;
        this.firstName = null;
    }

    public UserModel(String userid, String password, String firstName, String lastName, String role) {
        this.userid = userid;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public UserModel(UserEntity entity) {
        this.userid = entity.getUsername();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.password = entity.getPassword();
        this.role = entity.getRole();
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
