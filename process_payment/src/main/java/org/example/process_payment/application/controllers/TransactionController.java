package org.example.process_payment.application.controllers;

import org.example.process_payment.application.presenters.ReponseHandler;
import org.example.process_payment.domain.contracts.ListTransactions;
import org.example.process_payment.domain.entities.Transaction;
import org.example.process_payment.domain.useCases.CardTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class TransactionController {
    private CardTransaction cardTransaction;

    private ListTransactions list;
    @Autowired
    public TransactionController(CardTransaction cardTransaction, ListTransactions listTransactions) {
        this.cardTransaction = cardTransaction;
        this.list = listTransactions;
    }

    @PostMapping("/process-transaction")
    ResponseEntity<Object> process(@RequestBody Transaction transaction) {
        Transaction transactionResult = cardTransaction.process(transaction);
        return ReponseHandler.generateResponse(HttpStatus.OK, "Transaction stored successfully", transactionResult);
    }

    @GetMapping("/list-transactions")
    ResponseEntity<Object>list() {
        Transaction[] transactions = list.list().toArray(new Transaction[0]);
        return ReponseHandler.generateResponse(HttpStatus.OK, "Transactions recovered successfully", (Object) transactions);
    }
}
