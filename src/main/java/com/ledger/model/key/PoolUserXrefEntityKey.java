package com.ledger.model.key;

import com.ledger.model.PoolEntity;
import com.ledger.model.UserEntity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PoolUserXrefEntityKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "pool_name")
    private PoolEntity poolEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public PoolUserXrefEntityKey(PoolEntity poolEntity, UserEntity userEntity) {
        this.poolEntity = poolEntity;
        this.userEntity = userEntity;
    }

    public PoolEntity getPoolEntity() {
        return poolEntity;
    }

    public void setPoolEntity(PoolEntity poolEntity) {
        this.poolEntity = poolEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
