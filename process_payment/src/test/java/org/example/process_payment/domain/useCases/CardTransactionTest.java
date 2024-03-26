package org.example.process_payment.domain.useCases;

import org.example.process_payment.domain.entities.Transaction;
import org.example.process_payment.infra.Repositories.contracts.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CardTransactionTest {
    @Autowired
    private TransactionRepository repository;

    private Transaction transaction;

    private CardTransaction cardTransaction;

    private Timestamp date = new Timestamp(new Date(2024, 03, 05, 0, 0, 0).getTime());

    @Before
    public void setup() {
        cardTransaction = new CardTransaction(repository);
    }

    @Test
    public void shoudCallPaymentWithCorrectValues() {
        Transaction input = new Transaction(null,100, "any_trasaction", "debit_card", 8868, "any_person", date, 111);
        Transaction transactionResult = cardTransaction.process(input);
        assertEquals(input, transactionResult);
    }
}
