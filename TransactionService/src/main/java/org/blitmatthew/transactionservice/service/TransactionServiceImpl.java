package org.blitmatthew.transactionservice.service;

import lombok.extern.slf4j.Slf4j;
import org.blitmatthew.transactionservice.config.ApiUrlConfig;
import org.blitmatthew.transactionservice.dto.*;
import org.blitmatthew.transactionservice.entity.Transaction;
import org.blitmatthew.transactionservice.enums.TransactionStatus;
import org.blitmatthew.transactionservice.enums.TransactionType;
import org.blitmatthew.transactionservice.exception.InsufficientAccountBalanceException;
import org.blitmatthew.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiUrlConfig apiUrlConfig;

    @Override
    public MessageResponse createTransaction(PostNewTransaction request) {
        Transaction transaction = Transaction.builder()
                        .fromId(request.fromId())
                        .toId(request.toId())
                        .transactionType(request.transactionType())
                        .amount(request.amount())
                        .build();
        transactionRepository.save(transaction);
        Transaction finalTransaction = transactionRepository.findTopByOrderByIdDesc();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(finalTransaction.getId() > 0)
            {
                try {
                    updateTransaction(finalTransaction);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        return new MessageResponse("Transaction Posted");
    }

    @Override
    public List<TransactionInformation> getTransactionsByAccountId(Long id) {
        List<Transaction> transactions = transactionRepository.findTransactionsByToIdOrFromId(id, id);
        List<TransactionInformation> transactionInformations = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionInformation transactionInformation = new TransactionInformation(
                    transaction.getFromId(),
                    transaction.getToId(),
                    transaction.getAmount(),
                    transaction.getTransactionType(),
                    transaction.getTransactionStatus(),
                    transaction.getCreatedAt()
            );
            transactionInformations.add(transactionInformation);
        }
        return transactionInformations;

    }

    private void updateTransaction(Transaction transaction) throws URISyntaxException {
        log.info("MJM:TransactionServiceImpl:L82: Before Rest Template: " + apiUrlConfig.getAccountServiceUrl());
        ResponseEntity<AccountInformation> toAccount = restTemplate.getForEntity(new URI(apiUrlConfig.getAccountServiceUrl() + "/" + transaction.getToId()), AccountInformation.class);
        if(toAccount.getStatusCode().is4xxClientError() || toAccount.getStatusCode().is5xxServerError()) {
            throw new HttpClientErrorException(toAccount.getStatusCode());
        }
        if(transaction.getFromId() > 0 && transaction.getTransactionType() == TransactionType.WITHDRAW){
            ResponseEntity<AccountInformation> fromAccount = restTemplate.getForEntity(new URI(apiUrlConfig.getAccountServiceUrl() + "/" + transaction.getFromId()), AccountInformation.class);
            if(fromAccount.getStatusCode().is4xxClientError() || fromAccount.getStatusCode().is5xxServerError()) {
                throw new HttpClientErrorException(fromAccount.getStatusCode());
            }
            if(Objects.requireNonNull(fromAccount.getBody()).balance() >= transaction.getAmount()){
                transaction.setTransactionStatus(TransactionStatus.APPROVED);
                TransactionAction action = new TransactionAction(fromAccount.getBody().userProfileId(),fromAccount.getBody().id(), TransactionType.WITHDRAW, transaction.getAmount());
                ResponseEntity<?> response = restTemplate.patchForObject(apiUrlConfig + "/transaction-action", action, ResponseEntity.class);
                assert response != null;
                if(!response.getStatusCode().is2xxSuccessful()){
                    throw new HttpClientErrorException(response.getStatusCode());
                }
                transactionRepository.save(transaction);
                return;
            } else {
                transaction.setTransactionStatus(TransactionStatus.DECLINED);
                transactionRepository.save(transaction);
                throw new InsufficientAccountBalanceException("Account " + transaction.getFromId()+ " has insufficient balance");
            }
        }
        transaction.setTransactionStatus(TransactionStatus.APPROVED);
        TransactionAction action = new TransactionAction(toAccount.getBody().userProfileId(), toAccount.getBody().id(), TransactionType.DEPOSIT, transaction.getAmount());
        log.info(action.toString());
        restTemplate.put(new URI(apiUrlConfig.getAccountServiceUrl() + "/transaction-action"), action);
        transactionRepository.save(transaction);
    }
}
