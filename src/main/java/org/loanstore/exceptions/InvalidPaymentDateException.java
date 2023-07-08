package org.loanstore.exceptions;

import lombok.Getter;

@Getter
public class InvalidPaymentDateException extends LoanStoreException {
    private final String message;

    public InvalidPaymentDateException(String message) {
        super(message);
        this.message = message;
    }
}
