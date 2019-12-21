package com.ledger.repository;

import com.ledger.model.PoolEntity;
import com.ledger.model.PoolUserXref;
import com.ledger.model.UserEntity;
import com.ledger.model.key.PoolUserXrefEntityKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface XrefRepository extends CrudRepository<PoolUserXref, PoolUserXrefEntityKey> {

    List<PoolUserXref> getByEntityKey_UserEntity(UserEntity entity);

    List<PoolUserXref> getByEntityKey_PoolEntity(PoolEntity entity);
}
