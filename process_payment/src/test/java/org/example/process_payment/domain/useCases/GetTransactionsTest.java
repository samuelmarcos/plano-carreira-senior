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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GetTransactionsTest {
    @Autowired
    private TransactionRepository repository;

    private GetTransactions getTransactions;

    private Timestamp date = new Timestamp(new Date(2024, 03, 05, 0, 0, 0).getTime());

    @Before
    public void setup() {
        getTransactions = new GetTransactions(repository);
    }

    @Test
    public void shoudListTheCorrectTransactionsAfterSaveOneMore() {
        Transaction transaction = Transaction.builder()
                .transactionId(null)
                .transactionDate(date)
                .transactionDescription("exemplo")
                .transactionMethod("debit_card")
                .transactionValue(900)
                .cardCarrier("teste")
                .cardVerificationCode(333)
                .cardNumber(886888688)
                .build();
        repository.save(transaction);
        List<Transaction> listResult = getTransactions.list();
        assertEquals(listResult.size(), 3);
    }
}
