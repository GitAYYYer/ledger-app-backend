package com.ledger.model;

import javax.persistence.*;

@Entity
@Table(name = "transactions", schema = "ledger")
public class TransactionEntity {

    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "pool_id")
    private PoolEntity pool;

    @ManyToOne
    @JoinColumn(name = "lender_id")
    private UserEntity lender;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private UserEntity borrower;

    @Column(name = "pay_amount")
    private Double payAmount;

    @Column(name = "lender_vrfy")
    private Boolean payeeVerify;

    @Column(name = "borrower_vrfy")
    private Boolean oweeVerify;

    public TransactionEntity(PoolEntity pool, UserEntity lender, UserEntity borrower, Double payAmount) {
        this.pool = pool;
        this.lender = lender;
        this.borrower = borrower;
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

    public UserEntity getLender() {
        return lender;
    }

    public void setLender(UserEntity payee) {
        this.lender = payee;
    }

    public UserEntity getBorrower() {
        return borrower;
    }

    public void setBorrower(UserEntity owee) {
        this.borrower = owee;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
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

