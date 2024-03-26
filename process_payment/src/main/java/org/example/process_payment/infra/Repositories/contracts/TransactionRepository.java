package org.example.process_payment.infra.Repositories.contracts;

import org.example.process_payment.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
