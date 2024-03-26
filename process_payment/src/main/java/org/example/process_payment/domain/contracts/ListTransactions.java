package org.example.process_payment.domain.contracts;

import org.example.process_payment.domain.entities.Transaction;

import java.util.List;

public interface ListTransactions {
    List<Transaction> list();
}
