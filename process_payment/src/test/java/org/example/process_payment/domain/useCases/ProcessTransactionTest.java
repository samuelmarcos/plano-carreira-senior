package org.example.process_payment.domain.useCases;

import org.example.process_payment.domain.contracts.repos.SaveTransaction;
import org.example.process_payment.domain.entities.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProcessTransactionTest {
    @Mock
    private static SaveTransaction saveTransaction;

    private Transaction transaction;

    private ProcessTransaction processTransaction;

    private Timestamp date = new Timestamp(new Date(2024, 03, 05, 0, 0, 0).getTime());

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        processTransaction = new ProcessTransaction(saveTransaction);
    }

    @BeforeEach
    public void init() {
        transaction = new Transaction(100, "any_trasaction", "debit_card", 8868, "any_person", date, 111);
    }

    @Test
    public void shoudCallPaymentWithCorrectValues() {
        when(saveTransaction.save(transaction)).thenReturn(transaction);
        Transaction transactionResult = processTransaction.process(transaction);
        assertEquals(transaction, transactionResult);
    }

    @Test(expected = RuntimeException.class)
    public void shoudRethrowIfPaymentThrows() {
        when(saveTransaction.save(transaction)).thenThrow(RuntimeException.class);
        processTransaction.process(transaction);
        assertThrows(RuntimeException.class, ()-> processTransaction.process(transaction));
    }
}
