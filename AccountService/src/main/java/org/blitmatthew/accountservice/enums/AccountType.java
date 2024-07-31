package org.blitmatthew.accountservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {
    CHECKING("Checking"),
    SAVING("Saving");
    private final String accountType;
}
