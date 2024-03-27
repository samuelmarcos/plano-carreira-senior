package org.example.process_payment.domain.errors;

public class InvalidTransactionError extends  Exception {
    public InvalidTransactionError(String errorMessage) {
        super(errorMessage);
    }
}
