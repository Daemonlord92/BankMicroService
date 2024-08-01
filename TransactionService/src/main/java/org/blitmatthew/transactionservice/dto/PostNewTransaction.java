package org.blitmatthew.transactionservice.dto;

import org.blitmatthew.transactionservice.enums.TransactionType;

public record PostNewTransaction(
        long fromId,
        long toId,
        double amount,
        TransactionType transactionType
) {
}
