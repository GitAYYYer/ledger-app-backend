package com.ledger.repository;

import com.ledger.model.PoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PoolRepository extends CrudRepository<PoolEntity, Integer> {


}
