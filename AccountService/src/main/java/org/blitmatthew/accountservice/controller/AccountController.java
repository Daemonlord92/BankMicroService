package org.blitmatthew.accountservice.controller;

import org.blitmatthew.accountservice.dto.PostNewAccount;
import org.blitmatthew.accountservice.dto.TransactionAction;
import org.blitmatthew.accountservice.entity.Account;
import org.blitmatthew.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) throws AccountNotFoundException {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Account> createAccount(@RequestBody PostNewAccount account) throws AccountException {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PutMapping("/transaction-action")
    public ResponseEntity<Account> updateAccount(@RequestBody TransactionAction transactionAction) throws AccountException {
        return ResponseEntity.ok(accountService.updateAccount(transactionAction));
    }
}
