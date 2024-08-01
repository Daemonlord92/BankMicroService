package org.blitmatthew.transactionservice.dto;

import org.blitmatthew.transactionservice.enums.TransactionType;

public record TransactionAction(
        Long userProfileId,
        Long accountId,
        TransactionType action,
        Double amount
) {
}
