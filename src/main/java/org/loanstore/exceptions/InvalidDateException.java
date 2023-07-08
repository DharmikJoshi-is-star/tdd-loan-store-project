package org.loanstore.exceptions;

import lombok.Getter;

@Getter
public class InvalidDateException extends LoanStoreException {
    private final String message;

    public InvalidDateException(String message) {
        super(message);
        this.message = message;
    }
}
