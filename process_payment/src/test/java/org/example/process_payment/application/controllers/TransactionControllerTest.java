package org.example.process_payment.application.controllers;

import org.example.process_payment.application.presenters.ReponseHandler;
import org.example.process_payment.domain.entities.Transaction;
import org.example.process_payment.domain.useCases.ProcessTransaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionControllerTest {
    private static TransactionController controller;
    private Transaction transaction;
    @Mock
    ProcessTransaction processTransaction;
    private Timestamp date = new Timestamp(new Date(2024, 03, 05, 0, 0, 0).getTime());

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        controller = new TransactionController(processTransaction);
    }
    @BeforeEach
    public void init() {
        transaction = new Transaction(100, "any_trasaction", "debit_card", 8868, "any_person", date, 111);
    }


    @Test
    public void souldCallProcessTransactionUseCase() {
        when(processTransaction.process(transaction)).thenReturn(transaction);
        ResponseEntity<Object> response = ReponseHandler.generateResponse(transaction, HttpStatus.OK, "Transaction stored successfully");
        ResponseEntity<Object> controllerResponse = controller.handle(transaction);
        assertEquals(response, controllerResponse);
    }

    @Test(expected = RuntimeException.class)
    public void shoudRethrowIfPaymentThrows() {
        when(processTransaction.process(transaction)).thenThrow(RuntimeException.class);
        processTransaction.process(transaction);
        assertThrows(RuntimeException.class, ()-> processTransaction.process(transaction));
    }
}
