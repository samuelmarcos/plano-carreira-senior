package org.example.process_payment.application.controllers;

import org.example.process_payment.application.presenters.ReponseHandler;
import org.example.process_payment.domain.entities.Transaction;
import org.example.process_payment.domain.useCases.ProcessTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    private ProcessTransaction processTransaction;
    @Autowired
    public TransactionController(ProcessTransaction processTransaction) {
        this.processTransaction = processTransaction;
    }

    @PostMapping("/process-transaction")
    ResponseEntity<Object> handle(@RequestBody Transaction transaction) {
        Transaction transactionResult = processTransaction.process(transaction);
        return ReponseHandler.generateResponse(transactionResult, HttpStatus.OK, "Transaction stored successfully");
    }
}
