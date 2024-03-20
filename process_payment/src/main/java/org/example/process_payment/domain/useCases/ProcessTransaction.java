package org.example.process_payment.domain.useCases;

import org.example.process_payment.domain.contracts.CashIn;
import org.example.process_payment.domain.entities.Transaction;
import org.example.process_payment.infra.Repositories.Contracts.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessTransaction implements CashIn {
    private TransactionRepository repository;
    @Autowired
    public ProcessTransaction(TransactionRepository repository) {
        this.repository = repository;
    }
    @Override
    public Transaction process(Transaction transaction) {
        return this.repository.save(transaction);
    }
}
