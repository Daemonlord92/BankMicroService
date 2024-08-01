package org.blitmatthew.transactionservice.dto;

import org.blitmatthew.transactionservice.enums.TransactionStatus;
import org.blitmatthew.transactionservice.enums.TransactionType;

import java.time.LocalDateTime;

public record TransactionInformation(
        Long fromId,
        Long toId,
        Double amount,
        TransactionType transactionType,
        TransactionStatus transactionStatus,
        LocalDateTime createdAt
) {
}
