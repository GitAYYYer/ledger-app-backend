package com.ledger.repository;

import com.ledger.model.TransactionEntity;
import com.ledger.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {

    List<TransactionEntity> getByOweeOrPayee(UserEntity owee, UserEntity payee);
}
