package org.loanstore.validators.conditions;

import org.loanstore.entities.Loan;
import org.loanstore.exceptions.InvalidPaymentDateException;
import org.loanstore.utils.Util;

public class ValidatePaymentDate implements Condition {

    @Override
    public void validate(Object... arg) {
        Loan loan = (Loan) arg[0];
        boolean isPaymentDateGreaterThanDueDate = Util.parseDate(loan.getPaymentDate())
                .after(Util.parseDate(loan.getDueDate()));
        if (isPaymentDateGreaterThanDueDate) {
            throw new InvalidPaymentDateException(String.format(
                    "Payment date: %s is greater than due date: %s", loan.getPaymentDate(), loan.getDueDate()));
        }
    }
}
