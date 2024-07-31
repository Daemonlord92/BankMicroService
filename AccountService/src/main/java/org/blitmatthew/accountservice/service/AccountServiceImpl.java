package org.blitmatthew.accountservice.service;

import org.blitmatthew.accountservice.dto.PostNewAccount;
import org.blitmatthew.accountservice.dto.TransactionAction;
import org.blitmatthew.accountservice.entity.Account;
import org.blitmatthew.accountservice.enums.AccountAction;
import org.blitmatthew.accountservice.enums.AccountType;
import org.blitmatthew.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) throws AccountNotFoundException {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account with the id " + id));
    }

    public Account createAccount(PostNewAccount account) throws AccountException {
        Account newAccount = new Account();
        newAccount.setAccountType(AccountType.valueOf(account.accountType()));
        newAccount.setBalance(account.balance());
        newAccount.setUserProfileId(account.userProfileId());
        return accountRepository.save(newAccount);
    }

    public Account updateAccount(TransactionAction transactionAction) throws AccountException {
        Account account = getAccountById(transactionAction.accountId());
        if(transactionAction.action().equals(AccountAction.WITHDRAW)){
            account.setBalance(account.getBalance()-transactionAction.amount());
        }
        else if(transactionAction.action().equals(AccountAction.DEPOSIT)){
            account.setBalance(account.getBalance()+transactionAction.amount());
        }
        return accountRepository.save(account);
    }
}
