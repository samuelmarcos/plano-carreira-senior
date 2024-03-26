package org.example.process_payment.application.controllers;

import org.example.process_payment.application.presenters.ReponseHandler;
import org.example.process_payment.domain.contracts.ListTransactions;
import org.example.process_payment.domain.entities.Transaction;
import org.example.process_payment.domain.useCases.CardTransaction;
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

    private ListTransactions listTransactions;
    @Mock
    CardTransaction cardTransaction;
    private Timestamp date = new Timestamp(new Date(2024, 03, 05, 0, 0, 0).getTime());

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        controller = new TransactionController(cardTransaction, listTransactions);
    }
    @BeforeEach
    public void init() {
        transaction = new Transaction(null,100, "any_trasaction", "debit_card", 8868, "any_person", date, 111);
    }


    @Test
    public void souldCallProcessTransactionUseCase() {
        when(cardTransaction.process(transaction)).thenReturn(transaction);
        ResponseEntity<Object> response = ReponseHandler.generateResponse(HttpStatus.OK, "Transaction stored successfully", transaction);
        ResponseEntity<Object> controllerResponse = controller.process(transaction);
        assertEquals(response.getStatusCode(), controllerResponse.getStatusCode());
        assertEquals(response.getBody(), controllerResponse.getBody());
    }

    @Test(expected = RuntimeException.class)
    public void shoudRethrowIfPaymentThrows() {
        when(cardTransaction.process(transaction)).thenThrow(RuntimeException.class);
        cardTransaction.process(transaction);
        assertThrows(RuntimeException.class, ()-> cardTransaction.process(transaction));
    }
}
