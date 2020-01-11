package com.ledger.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ledger.model.UserEntity;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {

    @NotNull
    @JsonProperty("username")
    private String userid;

    @NotNull
    @JsonProperty("first_name")
    private String firstName;

    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    private String password;

    public UserModel(String userId) {
        this.userid = userId;
        lastName = null;
        firstName = null;
        password = null;
    }

    public UserModel(String userid, String firstName, String lastName, String password) {
        this.userid = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public UserModel() {
    }

    public UserModel(UserEntity entity) {
        this.userid = entity.getUserId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.password = null;
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
}
