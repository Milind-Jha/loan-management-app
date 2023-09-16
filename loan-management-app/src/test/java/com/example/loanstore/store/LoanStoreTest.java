package com.example.loanstore.store;

import com.example.loanstore.model.Loan;
import com.example.loanstore.repository.LoanRepository;
import com.example.loanstore.scheduler.LoanScheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class LoanStoreTest {
    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanScheduler loanStore;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckDueDateAlert() {
        // Given
        LocalDate currentDate = LocalDate.of(2023, 6, 6);
        Loan loan1 = new Loan("L1", "C1", "LEN1", 10000, 10000, LocalDate.of(2023, 6, 5), 1, LocalDate.of(2023, 7, 5), 0.01);
        Loan loan2 = new Loan("L2", "C1", "LEN1", 20000, 5000, LocalDate.of(2023, 6, 1), 1, LocalDate.of(2023, 8, 5), 0.01);
        List<Loan> loans = Arrays.asList(loan1, loan2);

        // Mock the repository method to return the loans
        when(loanRepository.findAll()).thenReturn(loans);

        // When
        loanStore.checkDueDateAlert();

        // Then
        verify(loanRepository, times(1)).findAll();
    }
}