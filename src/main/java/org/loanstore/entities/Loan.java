package org.loanstore.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Loan {
    private String loanId;
    private String customerId;
    private String lenderId;
    private Double amount;
    private Double remainingAmount;
    private String paymentDate;
    private Double interestPerDay;
    private String dueDate;
    private Double penaltyPerDay;
    private Boolean cancel;
}
