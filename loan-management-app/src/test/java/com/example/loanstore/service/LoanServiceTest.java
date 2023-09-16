package com.example.loanstore.service;

import com.example.loanstore.model.Loan;
import com.example.loanstore.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class LoanServiceTest {
    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddLoanPaymentDateBeforeDueDate() {
        // Given
        Loan loan = new Loan();
        loan.setPaymentDate(LocalDate.of(2023, 6, 5));
        loan.setDueDate(LocalDate.of(2023, 6, 1));

        // When
        Exception exception = assertThrows(Exception.class, () -> loanService.addLoan(loan));

        // Then
        assertEquals("Payment date can't be greater than due date.", exception.getMessage());
    }

    @Test
    public void testAddLoanPaymentDateAfterDueDate() throws Exception {
        // Given
        Loan loan = new Loan();
        loan.setPaymentDate(LocalDate.of(2023, 6, 5));
        loan.setDueDate(LocalDate.of(2023, 6, 6));

        // Mock the repository save method to return the loan
        when(loanRepository.save(loan)).thenReturn(loan);

        // When
        Loan savedLoan = loanService.addLoan(loan);

        // Then
        assertEquals(loan, savedLoan);
    }

    @Test
    public void testAggregateByLender() {
        // Given
        Object[] lender1Data = new Object[] {"LEN1", 30000.0, 0.02, 0.02};
        List<Object[]> lenderDataList = new ArrayList<>();
        lenderDataList.add(lender1Data);

        // Mock the repository method to return the lender data
        when(loanRepository.aggregateByLender()).thenReturn(lenderDataList);

        // When
        List<Object[]> result = loanService.aggregateByLender();

        // Then
        assertEquals(1, result.size());
        Object[] resultData = result.get(0);
        assertEquals("LEN1", resultData[0]);
        assertEquals(30000.0, resultData[1]);
        assertEquals(0.02, resultData[2]);
        assertEquals(0.02, resultData[3]);
    }

    @Test
    public void testAggregateByInterest() {
        // Given
        Object[] interest1Data = new Object[] {0.01, 30000.0, "LEN1,LEN2", "C1,C2"};
        List<Object[]> interestDataList = new ArrayList<>();
        interestDataList.add(interest1Data);

        // Mock the repository method to return the interest data
        when(loanRepository.aggregateByInterest()).thenReturn(interestDataList);

        // When
        List<Object[]> result = loanService.aggregateByInterest();

        // Then
        assertEquals(1, result.size());
        Object[] resultData = result.get(0);
        assertEquals(0.01, resultData[0]);
        assertEquals(30000.0, resultData[1]);
        assertEquals("LEN1,LEN2", resultData[2]);
        assertEquals("C1,C2", resultData[3]);
    }

    @Test
    public void testAggregateByCustomerId() {
        // Given
        Object[] customer1Data = new Object[] {"C1", 30000.0, "LEN1,LEN2", 0.03, 0.03};
        List<Object[]> customerDataList = new ArrayList<>();
        customerDataList.add(customer1Data);

        // Mock the repository method to return the customer data
        when(loanRepository.aggregateByCustomerId()).thenReturn(customerDataList);

        // When
        List<Object[]> result = loanService.aggregateByCustomerId();

        // Then
        assertEquals(1, result.size());
        Object[] resultData = result.get(0);
        assertEquals("C1", resultData[0]);
        assertEquals(30000.0, resultData[1]);
        assertEquals("LEN1,LEN2", resultData[2]);
        assertEquals(0.03, resultData[3]);
        assertEquals(0.03, resultData[4]);
    }
}