package org.blitmatthew.accountservice.service;

import org.blitmatthew.accountservice.dto.PostNewAccount;
import org.blitmatthew.accountservice.dto.TransactionAction;
import org.blitmatthew.accountservice.entity.Account;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountById(Long id) throws AccountNotFoundException;
    Account createAccount(PostNewAccount account) throws AccountException;
    Account updateAccount(TransactionAction transactionAction) throws AccountException;
}
