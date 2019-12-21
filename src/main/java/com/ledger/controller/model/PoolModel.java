package com.ledger.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PoolModel {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String desc;

    public PoolModel(String name, String desc) {
        this.name = name;
        this.desc = desc;
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
