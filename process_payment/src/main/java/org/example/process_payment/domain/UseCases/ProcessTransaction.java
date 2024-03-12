package org.example.process_payment.domain.UseCases;

import org.example.process_payment.domain.contracts.CashIn;
import org.example.process_payment.domain.contracts.Repos.SaveTransaction;
import org.example.process_payment.domain.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessTransaction implements CashIn {
    private SaveTransaction saveTransaction;
    @Autowired
    public ProcessTransaction(SaveTransaction saveTransaction) {
        this.saveTransaction = saveTransaction;
    }
    @Override
    public Transaction process(Transaction transaction) {
        return this.saveTransaction.save(transaction);
    }
}
