package org.blitmatthew.accountservice.dto;

import org.blitmatthew.accountservice.annotation.ValidTransactionAction;
import org.blitmatthew.accountservice.enums.AccountAction;

@ValidTransactionAction
public record TransactionAction(
        Long userProfileId,
        Long accountId,
        AccountAction action,
        Double amount
) {
}
