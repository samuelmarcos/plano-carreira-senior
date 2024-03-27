package org.example.process_payment.application.controllers;

import org.example.process_payment.application.presenters.ReponseHandler;
import org.example.process_payment.domain.contracts.ListTransactions;
import org.example.process_payment.domain.entities.Transaction;
import org.example.process_payment.domain.errors.InvalidTransactionError;
import org.example.process_payment.domain.useCases.CardTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        try {
            Date trasactionDate = new Date(transaction.getDate().getTime());
            long duration = new Date().getTime() - trasactionDate.getTime();
            if (TimeUnit.MILLISECONDS.toMinutes(duration) < 5) {
                return ReponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "InvalidTransaction", new InvalidTransactionError("5 min time between transactions was not respected"));
            }
            Transaction transactionResult = cardTransaction.process(transaction);
            return ReponseHandler.generateResponse(HttpStatus.OK, "Transaction stored successfully", transactionResult);
        } catch (Exception  e) {
            return ReponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred, please try again later", e.getMessage());
        }
    }

    @GetMapping("/list-transactions")
    ResponseEntity<Object>list() {
        try {
            List<Transaction> transactions = list.list();
            return ReponseHandler.generateResponse(HttpStatus.OK, "Transactions recovered successfully", transactions);
        } catch (Exception e) {
            return ReponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred, please try again later", e.getMessage());
        }
    }
}
