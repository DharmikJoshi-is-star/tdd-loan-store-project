import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.loanstore.entities.Loan;
import org.loanstore.exceptions.InvalidPaymentDateException;
import org.loanstore.services.LoanStore;
import utils.LoanDataHelper;

import java.util.ArrayList;
import java.util.Map;

public class LoanStoreTest {
    private LoanStore loanStore;

    @BeforeEach
    public void initialize() {
        loanStore = new LoanStore(new ArrayList<>());
    }

    /**
     * Test: To add a valid Loan
     */
    @Test
    public void testAddLoan() {
        Loan loan = Loan.builder()
                .loanId("L1")
                .customerId("C1")
                .lenderId("LEN1")
                .amount(10000d)
                .remainingAmount(10000d)
                .paymentDate("05/06/2023")
                .interestPerDay(1d)
                .dueDate("05/07/2023")
                .penaltyPerDay(0.01)
                .build();
        loanStore.addLoan(loan);
        Assertions.assertEquals(1, loanStore.getLoans().size());
    }

    /**
     * Test: To test valid Payment Date before adding loan
     */
    @Test
    public void testAddLoanWhenPaymetDateIsGreaterThanDueDate() {
        Loan loan = Loan.builder()
                .loanId("L1")
                .customerId("C1")
                .lenderId("LEN1")
                .amount(10000d)
                .remainingAmount(10000d)
                .paymentDate("05/08/2023")
                .interestPerDay(1d)
                .dueDate("05/07/2023")
                .penaltyPerDay(0.01)
                .build();
        Assertions.assertThrowsExactly(InvalidPaymentDateException.class, () -> loanStore.addLoan(loan));
    }

    /**
     * Aggregation of Remaining Loan Amount By Lender
     */
    @Test
    public void aggregateRemainingLoanAmountByLender() {
        loanStore.addLoans(LoanDataHelper.getLoansToTestAggregation());
        Map<String, Double> aggregateRemainingLoanAmountByLender = loanStore.aggregateRemainingLoanAmountByLender();
        Assertions.assertEquals(LoanDataHelper.getExpectedResultForAggregateRemainingLoanAmountByLender(),
                aggregateRemainingLoanAmountByLender);
    }

    /**
     * Test: Aggregation of Remaining Interest per Day(%) By Lender
     */
    @Test
    public void aggregateInterestByLender() {
        loanStore.addLoans(LoanDataHelper.getLoansToTestAggregation());
        Map<String, Double> aggregateInterestByLender = loanStore.aggregateInterestByLender();
        Assertions.assertEquals(LoanDataHelper.getExpectedResultForAggregateInterestByLender(), aggregateInterestByLender);
    }

    /**
     * Test: Aggregation of Remaining Penalty/Day(%) By Lender
     */
    @Test
    public void aggregatePenaltyByLender() {
        loanStore.addLoans(LoanDataHelper.getLoansToTestAggregation());
        Map<String, Double> aggregatePenaltyByLender = loanStore.aggregatePenaltyByLender();
        Assertions.assertEquals(LoanDataHelper.getExpectedResultForAggregatePenaltyByLender(), aggregatePenaltyByLender);
    }


    /**
     * Test: Aggregation of Remaining Loan Amount By CustomerId
     */
    @Test
    public void aggregateRemainingLoanAmountByCustomerId() {
        loanStore.addLoans(LoanDataHelper.getLoansToTestAggregation());
        Map<String, Double> aggregateRemainingLoanAmountByCustomerId = loanStore.aggregateRemainingLoanAmountByCustomerId();
        Assertions.assertEquals(LoanDataHelper.getExpectedResultForAggregateRemainingLoanAmountByCustomerId(),
                aggregateRemainingLoanAmountByCustomerId);
    }

    /**
     * Test: Aggregation of Remaining Interest per Day(%) By CustomerId
     */
    @Test
    public void aggregateInterestByCustomerId() {
        loanStore.addLoans(LoanDataHelper.getLoansToTestAggregation());
        Map<String, Double> aggregateInterestByCustomerId = loanStore.aggregateInterestByCustomerId();
        Assertions.assertEquals(LoanDataHelper.getExpectedResultForAggregateInterestByCustomerId(), aggregateInterestByCustomerId);
    }

    /**
     * Test: Aggregation of Remaining Penalty/Day(%) By CustomerId
     */
    @Test
    public void aggregatePenaltyByCustomerId() {
        loanStore.addLoans(LoanDataHelper.getLoansToTestAggregation());
        Map<String, Double> aggregatePenaltyByCustomerId = loanStore.aggregatePenaltyByCustomerId();
        Assertions.assertEquals(LoanDataHelper.getExpectedResultForAggregatePenaltyByCustomerId(), aggregatePenaltyByCustomerId);
    }


    /**
     * Test: Aggregation of Remaining Loan Amount By Interest
     */
    @Test
    public void aggregateRemainingLoanAmountByInterest() {
        loanStore.addLoans(LoanDataHelper.getLoansToTestAggregation());
        Map<Double, Double> aggregateRemainingLoanAmountByInterest = loanStore.aggregateRemainingLoanAmountByInterest();
        Assertions.assertEquals(LoanDataHelper.getExpectedResultForAggregateRemainingLoanAmountByInterest(),
                aggregateRemainingLoanAmountByInterest);
    }

    /**
     * Test: Aggregation of Remaining Interest per Day(%) By Interest
     */
    @Test
    public void aggregateInterestByInterest() {
        loanStore.addLoans(LoanDataHelper.getLoansToTestAggregation());
        Map<Double, Double> aggregateInterestByInterest = loanStore.aggregateInterestByInterest();
        Assertions.assertEquals(LoanDataHelper.getExpectedResultForAggregateInterestByInterest(), aggregateInterestByInterest);
    }

    /**
     * Test: Aggregation of Remaining Penalty/Day(%) By Interest
     */
    @Test
    public void aggregatePenaltyByInterest() {
        loanStore.addLoans(LoanDataHelper.getLoansToTestAggregation());
        Map<Double, Double> aggregatePenaltyByInterest = loanStore.aggregatePenaltyByInterest();
        Assertions.assertEquals(LoanDataHelper.getExpectedResultForAggregatePenaltyByInterest(), aggregatePenaltyByInterest);
    }

    /**
     * Test: To test due date check
     * If due date is missed loanStore.getLogger() will contain the following message
     * "Due date is missed for loan id: L1"
     * */
    @Test
    public void checkDueDates() {
        loanStore.addLoan(Loan.builder()
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
        loanStore.checkDueDateForAllLoans();
        Assertions.assertTrue(loanStore.getLogger().contains("Due date is missed for loan id: L1"));
    }

}
