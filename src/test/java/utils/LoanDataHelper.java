package utils;

import org.loanstore.entities.Loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanDataHelper {

    public static Map<String, Double> getExpectedResultForAggregateRemainingLoanAmountByLender() {
        Map<String, Double> expectedResult = new HashMap<>();
        expectedResult.put("LEN1", 15000.0);
        expectedResult.put("LEN2", 60000.0);
        return expectedResult;
    }

    public static Map<String, Double> getExpectedResultForAggregateInterestByLender() {
        Map<String, Double> expectedResult = new HashMap<>();
        expectedResult.put("LEN1", 2d);
        expectedResult.put("LEN2", 4d);
        return expectedResult;
    }

    public static Map<String, Double> getExpectedResultForAggregatePenaltyByLender() {
        Map<String, Double> expectedResult = new HashMap<>();
        expectedResult.put("LEN1", 0.02);
        expectedResult.put("LEN2", 0.04);
        return expectedResult;
    }

    public static Map<String, Double> getExpectedResultForAggregateRemainingLoanAmountByCustomerId() {
        Map<String, Double> expectedResult = new HashMap<>();
        expectedResult.put("C1", 15000.0);
        expectedResult.put("C2", 30000.0);
        expectedResult.put("C3", 30000.0);
        return expectedResult;
    }

    public static Map<String, Double> getExpectedResultForAggregateInterestByCustomerId() {
        Map<String, Double> expectedResult = new HashMap<>();
        expectedResult.put("C1", 2d);
        expectedResult.put("C2", 2d);
        expectedResult.put("C3", 2d);
        return expectedResult;
    }

    public static Map<String, Double> getExpectedResultForAggregatePenaltyByCustomerId() {
        Map<String, Double> expectedResult = new HashMap<>();
        expectedResult.put("C1", 0.02);
        expectedResult.put("C2", 0.02);
        expectedResult.put("C3", 0.02);
        return expectedResult;
    }


    public static Map<Double, Double> getExpectedResultForAggregateRemainingLoanAmountByInterest() {
        Map<Double, Double> expectedResult = new HashMap<>();
        expectedResult.put(1d, 15000.0);
        expectedResult.put(2d, 60000.0);
        return expectedResult;
    }

    public static Map<Double, Double> getExpectedResultForAggregateInterestByInterest() {
        Map<Double, Double> expectedResult = new HashMap<>();
        expectedResult.put(1d, 2d);
        expectedResult.put(2d, 4d);
        return expectedResult;
    }

    public static Map<Double, Double> getExpectedResultForAggregatePenaltyByInterest() {
        Map<Double, Double> expectedResult = new HashMap<>();
        expectedResult.put(1d, 0.02);
        expectedResult.put(2d, 0.04);
        return expectedResult;
    }

    public static List<Loan> getLoansToTestAggregation() {
        List<Loan> loanList = new ArrayList<>();
        loanList.add(Loan.builder()
                .loanId("L1")
                .customerId("C1")
                .lenderId("LEN1")
                .amount(10000d)
                .remainingAmount(10000d)
                .paymentDate("05/06/2023")
                .interestPerDay(1d)
                .dueDate("05/07/2023")
                .penaltyPerDay(0.01)
                .build());
        loanList.add(Loan.builder()
                .loanId("L2")
                .customerId("C1")
                .lenderId("LEN1")
                .amount(20000d)
                .remainingAmount(5000d)
                .paymentDate("01/06/2023")
                .interestPerDay(1d)
                .dueDate("05/08/2023")
                .penaltyPerDay(0.01)
                .build());
        loanList.add(Loan.builder()
                .loanId("L3")
                .customerId("C2")
                .lenderId("LEN2")
                .amount(50000d)
                .remainingAmount(30000d)
                .paymentDate("04/04/2023")
                .interestPerDay(2d)
                .dueDate("04/05/2023")
                .penaltyPerDay(0.02)
                .build());
        loanList.add(Loan.builder()
                .loanId("L4")
                .customerId("C3")
                .lenderId("LEN2")
                .amount(50000d)
                .remainingAmount(30000d)
                .paymentDate("04/04/2023")
                .interestPerDay(2d)
                .dueDate("04/05/2023")
                .penaltyPerDay(0.02)
                .build());
        return loanList;
    }
}
