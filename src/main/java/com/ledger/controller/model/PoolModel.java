package com.ledger.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ledger.model.PoolEntity;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PoolModel {

    @JsonProperty("pool_name")
    private String name;

    @JsonProperty("description")
    private String desc;

    @JsonProperty
    private List<UserModel> users;

    public PoolModel(String name, String desc) {
        this.name = name;
        this.desc = desc;

        users = new ArrayList<>();
    }

    public PoolModel(PoolEntity entity) {
        this.name = entity.getPoolName();
        this.desc = entity.getPoolDesc();

        users = new ArrayList<>();
    }

    public void addUser(UserModel model) {
        users.add(model);
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public PoolModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
