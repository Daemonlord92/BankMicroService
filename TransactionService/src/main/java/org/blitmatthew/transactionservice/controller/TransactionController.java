package org.blitmatthew.transactionservice.controller;

import org.blitmatthew.transactionservice.dto.MessageResponse;
import org.blitmatthew.transactionservice.dto.PostNewTransaction;
import org.blitmatthew.transactionservice.dto.TransactionInformation;
import org.blitmatthew.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<MessageResponse> postNewTransaction(@RequestBody PostNewTransaction request) {
        return ResponseEntity.ok(transactionService.createTransaction(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionInformation>> getTransactionsById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(id));
    }
}
