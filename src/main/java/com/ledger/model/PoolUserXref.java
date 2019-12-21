package com.ledger.model;


import com.ledger.model.key.PoolUserXrefEntityKey;

import javax.persistence.*;

@Entity
@Table(name = "pooluserxref")
public class PoolUserXref {

    @EmbeddedId
    @JoinColumns({
            @JoinColumn(name = "pool_id"),
            @JoinColumn(name  = "user_id")
    })
    private PoolUserXrefEntityKey entityKey;

    public PoolUserXref() {
    }

    public PoolUserXref(PoolUserXrefEntityKey entityKey) {
        this.entityKey = entityKey;
    }

    public PoolUserXrefEntityKey getEntityKey() {
        return entityKey;
    }

    public void setEntityKey(PoolUserXrefEntityKey entityKey) {
        this.entityKey = entityKey;
    }
}
