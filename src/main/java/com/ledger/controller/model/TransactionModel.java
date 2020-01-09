package com.ledger.controller.model;

public class TransactionModel {


    private String oweeId;

    private String payeeId;

    private Double ammount;

    public TransactionModel(String oweeId, String payeeId, Double ammount) {
        this.oweeId = oweeId;
        this.payeeId = payeeId;
        this.ammount = ammount;
    }

    public String getOweeId() {
        return oweeId;
    }

    public void setOweeId(String oweeId) {
        this.oweeId = oweeId;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public Double getAmmount() {
        return ammount;
    }

    public void setAmmount(Double ammount) {
        this.ammount = ammount;
    }
}
