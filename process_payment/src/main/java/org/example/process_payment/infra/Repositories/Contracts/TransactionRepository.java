package org.example.process_payment.infra.Repositories.Contracts;

import org.example.process_payment.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
