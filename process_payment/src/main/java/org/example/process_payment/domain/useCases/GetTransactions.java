package org.example.process_payment.domain.useCases;

import org.example.process_payment.domain.contracts.ListTransactions;
import org.example.process_payment.domain.entities.Transaction;
import org.example.process_payment.infra.Repositories.contracts.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetTransactions implements ListTransactions  {
    private final TransactionRepository repository;
    @Autowired
    public GetTransactions(TransactionRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Transaction> list() {
        return this.repository.findAll();
    }
}
