package org.blitmatthew.accountservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountAction {
    WITHDRAW("Withdraw"),
    DEPOSIT("Deposit");
    private String action;
}
