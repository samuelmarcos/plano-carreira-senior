package org.example.process_payment.domain.contracts.Repos;

import org.example.process_payment.domain.entities.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveTransaction {

    Transaction save(Transaction transaction);
}
