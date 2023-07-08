package org.loanstore.validators;

import lombok.Getter;
import org.loanstore.entities.Loan;
import org.loanstore.validators.conditions.Condition;
import org.loanstore.validators.conditions.ValidatePaymentDate;

import java.util.Collections;
import java.util.List;

@Getter
public class AddLoanValidator {
    private final List<Condition> addLoanConditions;

    public AddLoanValidator() {
        this.addLoanConditions = Collections.singletonList(new ValidatePaymentDate());
    }

    public void validate(Loan loan) {
        for (Condition condition : addLoanConditions) {
            condition.validate(loan);
        }
    }
}
