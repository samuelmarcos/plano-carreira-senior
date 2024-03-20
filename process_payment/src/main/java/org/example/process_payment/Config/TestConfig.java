package org.example.process_payment.Config;

import org.example.process_payment.domain.entities.Transaction;
import org.example.process_payment.infra.Repositories.Contracts.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private TransactionRepository transactionRepository;

    private Timestamp date = new Timestamp(new Date(2024, 03, 05, 0, 0, 0).getTime());
    @Override
    public void run(String... args) throws Exception {
        Transaction t1 = new Transaction(null,1000, "any_trasaction1", "debit_card", 8868, "any_person", date, 111);
        Transaction t2 = new Transaction(null,1200, "any_trasaction2", "credit_card", 8868, "any_person2", date, 211);

        transactionRepository.saveAll(Arrays.asList(t1, t2));
    }
}
