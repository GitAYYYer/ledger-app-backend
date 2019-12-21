package com.ledger.model;

import com.ledger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "pool")
public class PoolEntity {

    @Id
    @Column(name = "pool_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer poolId;

    @Column(name = "pool_name")
    private String poolName;

    @Column(name = "pool_desc")
    private String poolDesc;


    public PoolEntity(String poolName, String poolDesc) {
        this.poolName = poolName;
        this.poolDesc = poolDesc;
    }

    public PoolEntity() {
    }

    public Integer getPoolId() {
        return poolId;
    }

    public void setPoolId(Integer poolId) {
        this.poolId = poolId;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public String getPoolDesc() {
        return poolDesc;
    }

    public void setPoolDesc(String poolDesc) {
        this.poolDesc = poolDesc;
    }
}
