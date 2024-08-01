package org.blitmatthew.transactionservice.service;

import org.blitmatthew.transactionservice.dto.MessageResponse;
import org.blitmatthew.transactionservice.dto.PostNewTransaction;
import org.blitmatthew.transactionservice.dto.TransactionInformation;

import java.util.List;

public interface TransactionService {
    MessageResponse createTransaction(PostNewTransaction request);
    List<TransactionInformation> getTransactionsByAccountId(Long id);
}
