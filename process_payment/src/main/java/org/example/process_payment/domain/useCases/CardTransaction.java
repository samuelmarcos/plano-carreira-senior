package org.example.process_payment.domain.useCases;

import org.example.process_payment.domain.contracts.CashIn;
import org.example.process_payment.domain.entities.Transaction;
import org.example.process_payment.infra.Repositories.contracts.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardTransaction implements CashIn {
    private final TransactionRepository repository;
    @Autowired
    public CardTransaction(TransactionRepository repository) {
        this.repository = repository;
    }
    @Override
    public Transaction process(Transaction transaction) {
        return this.repository.save(transaction);
    }
}
