package org.blitmatthew.transactionservice.repository;

import org.blitmatthew.transactionservice.entity.Transaction;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {
    Transaction findTopByOrderByIdDesc();
    List<Transaction> findTransactionsByToIdOrFromId(Long toId, Long fromId);
}
