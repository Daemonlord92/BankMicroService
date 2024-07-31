package org.blitmatthew.accountservice.dto;

import org.blitmatthew.accountservice.annotation.PostNewAccountValid;

@PostNewAccountValid
public record PostNewAccount(
        String accountType,
        Double balance,
        Long userProfileId
) {
}
