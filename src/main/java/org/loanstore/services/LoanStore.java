package org.loanstore.services;

import lombok.Getter;
import org.loanstore.entities.Loan;
import org.loanstore.utils.Util;
import org.loanstore.validators.AddLoanValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LoanStore {
    @Getter
    private final List<String> logger;
    @Getter
    private final List<Loan> loans;
    private final AddLoanValidator addLoanValidator;

    public LoanStore(List<Loan> loans) {
        this.logger = new ArrayList<>();
        this.loans = loans;
        this.addLoanValidator = new AddLoanValidator();
    }

    public void addLoan(Loan loan) {
        addLoanValidator.validate(loan);
        this.loans.add(loan);
    }

    public void addLoans(List<Loan> loans) {
        loans.forEach(this::addLoan);
    }

    public Map<String, Double> aggregateRemainingLoanAmountByLender() {
        return aggregateByLender(Loan::getRemainingAmount);
    }

    public Map<String, Double> aggregateInterestByLender() {
        return aggregateByLender(Loan::getInterestPerDay);
    }

    public Map<String, Double> aggregatePenaltyByLender() {
        return aggregateByLender(Loan::getPenaltyPerDay);
    }

    public Map<String, Double> aggregateRemainingLoanAmountByCustomerId() {
        return aggregateByCustomerId(Loan::getRemainingAmount);
    }

    public Map<String, Double> aggregateInterestByCustomerId() {
        return aggregateByCustomerId(Loan::getInterestPerDay);
    }

    public Map<String, Double> aggregatePenaltyByCustomerId() {
        return aggregateByCustomerId(Loan::getPenaltyPerDay);
    }

    public Map<Double, Double> aggregateRemainingLoanAmountByInterest() {
        return aggregateByInterestPerDay(Loan::getRemainingAmount);
    }

    public Map<Double, Double> aggregateInterestByInterest() {
        return aggregateByInterestPerDay(Loan::getInterestPerDay);
    }

    public Map<Double, Double> aggregatePenaltyByInterest() {
        return aggregateByInterestPerDay(Loan::getPenaltyPerDay);
    }

    private Map<String, Double> aggregateByLender(Function<Loan, Double> attributeExtractor) {
        return this.loans.stream()
                .collect(Collectors.groupingBy(Loan::getLenderId, Collectors.summingDouble(attributeExtractor::apply)));
    }

    private Map<String, Double> aggregateByCustomerId(Function<Loan, Double> attributeExtractor) {
        return this.loans.stream()
                .collect(Collectors.groupingBy(Loan::getCustomerId, Collectors.summingDouble(attributeExtractor::apply)));
    }

    private Map<Double, Double> aggregateByInterestPerDay(Function<Loan, Double> attributeExtractor) {
        return this.loans.stream()
                .collect(Collectors.groupingBy(Loan::getInterestPerDay, Collectors.summingDouble(attributeExtractor::apply)));
    }

    public void checkDueDateForAllLoans() {
        String currentDate = Util.getCurrentDate();
        this.loans.forEach(loan -> {
            if (Util.parseDate(loan.getDueDate()).before(Util.parseDate(currentDate))) {
                logger.add(String.format("Due date is missed for loan id: %s", loan.getLoanId()));
            }
        });
    }
}
