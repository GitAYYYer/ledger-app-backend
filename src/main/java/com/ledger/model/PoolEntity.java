package com.ledger.model;

import com.ledger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "pool")
public class PoolEntity {

    @Id
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
