package com.ledger.model;

import javax.persistence.*;

@Entity
@Table(name = "transactions", schema = "ledger")
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "pool_name")
    private PoolEntity pool;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private UserEntity payee;

    @ManyToOne
    @JoinColumn(name = "owee_id")
    private UserEntity owee;

    @Column(name = "pay_amount")
    private Integer payAmount;

    @Column(name = "payee_vrfy")
    private Boolean payeeVerify;

    @Column(name = "owee_vrfy")
    private Boolean oweeVerify;

    public Transaction(PoolEntity pool, UserEntity payee, UserEntity owee, Integer payAmount) {
        this.pool = pool;
        this.payee = payee;
        this.owee = owee;
        this.payAmount = payAmount;
        this.payeeVerify = false;
        this.oweeVerify = false;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public PoolEntity getPool() {
        return pool;
    }

    public void setPool(PoolEntity pool) {
        this.pool = pool;
    }

    public UserEntity getPayee() {
        return payee;
    }

    public void setPayee(UserEntity payee) {
        this.payee = payee;
    }

    public UserEntity getOwee() {
        return owee;
    }

    public void setOwee(UserEntity owee) {
        this.owee = owee;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Boolean getPayeeVerify() {
        return payeeVerify;
    }

    public void setPayeeVerify(Boolean payeeVerify) {
        this.payeeVerify = payeeVerify;
    }

    public Boolean getOweeVerify() {
        return oweeVerify;
    }

    public void setOweeVerify(Boolean oweeVerify) {
        this.oweeVerify = oweeVerify;
    }
}

