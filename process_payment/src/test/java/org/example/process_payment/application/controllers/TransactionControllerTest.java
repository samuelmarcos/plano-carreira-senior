package org.example.process_payment.application.controllers;

import org.example.process_payment.application.presenters.ReponseHandler;
import org.example.process_payment.domain.contracts.ListTransactions;
import org.example.process_payment.domain.entities.Transaction;
import org.example.process_payment.domain.errors.InvalidTransactionError;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionControllerTest {
    private static TransactionController controller;
    private Transaction transaction;
    @Mock
    ListTransactions listTransactions;
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
        Transaction input = Transaction.builder()
                .transactionId(null)
                .transactionDate(new Timestamp(new Date().getTime()))
                .transactionDescription("exemplo")
                .transactionMethod("debit_card")
                .transactionValue(900)
                .cardCarrier("teste")
                .cardVerificationCode(333)
                .cardNumber(886888688)
                .build();
        when(cardTransaction.process(input)).thenReturn(input);
        ResponseEntity<Object> response = ReponseHandler.generateResponse(HttpStatus.OK, "Transaction stored successfully", transaction);
        ResponseEntity<Object> controllerResponse = controller.process(input);
        assertEquals(response.getStatusCode(), controllerResponse.getStatusCode());
        assertEquals(response.getBody(), controllerResponse.getBody());
    }

    @Test
    public void shoudReturnBadRequestWhenTransactionDateISInvalid() {
        Transaction input = Transaction.builder()
                .transactionId(null)
                .transactionDate(date)
                .transactionDescription("exemplo")
                .transactionMethod("debit_card")
                .transactionValue(900)
                .cardCarrier("teste")
                .cardVerificationCode(333)
                .cardNumber(886888688)
                .build();
        when(cardTransaction.process(input)).thenReturn(input);
        ResponseEntity<Object> response = ReponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "InvalidTransaction", new InvalidTransactionError("5 min time between transactions was not respected"));
        ResponseEntity<Object> controllerResponse = controller.process(input);
        assertEquals(response.getStatusCode(), controllerResponse.getStatusCode());
        assertEquals(response.getBody(), controllerResponse.getBody());
    }


    @Test(expected = Exception.class)
    public void shoudReturnServerErrorIfCardTransactionThrows() {
        when(cardTransaction.process(transaction)).thenThrow(Exception.class);
        ResponseEntity<Object> controllerResponse = controller.process(transaction);
        assertEquals(controllerResponse.getStatusCode(), 500);
        assertThrows(RuntimeException.class, ()-> cardTransaction.process(transaction));
    }

    @Test
    public void souldCallListTransactionUseCase() {
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
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(listTransactions.list()).thenReturn(transactions);
        ResponseEntity<Object> response = ReponseHandler.generateResponse(HttpStatus.OK, "Transactions recovered successfully", transactions);
        ResponseEntity<Object> controllerResponse = controller.list();
        assertEquals(response.getStatusCode(), controllerResponse.getStatusCode());
        assertEquals(response.getBody(), controllerResponse.getBody());
    }

    @Test(expected = Exception.class)
    public void shoudReturnServerErrorIfListTransactionsThrows() {
        when(listTransactions.list()).thenThrow(Exception.class);
        ResponseEntity<Object> controllerResponse = controller.list();
        assertEquals(controllerResponse.getStatusCode(), 500);
        assertThrows(Exception.class, ()-> listTransactions.list());
    }

}
