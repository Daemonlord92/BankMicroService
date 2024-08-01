package org.blitmatthew.transactionservice.dto;

public record AccountInformation(
        Long id,
        Long userProfileId,
        Double balance
) {
}
