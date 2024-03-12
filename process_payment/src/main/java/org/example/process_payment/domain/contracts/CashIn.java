package org.example.process_payment.domain.contracts;

import org.example.process_payment.domain.entities.Transaction;

public interface CashIn {
    Transaction process(Transaction transaction);
}
