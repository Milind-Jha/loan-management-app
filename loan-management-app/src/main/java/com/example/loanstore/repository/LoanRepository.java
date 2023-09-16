package com.example.loanstore.repository;

import com.example.loanstore.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LoanRepository extends JpaRepository<Loan, String> {

    @Query(value = "SELECT l.lenderId, SUM(l.remainingAmount) AS totalRemainingAmount, SUM(l.interestPerDay) AS totalInterestPerDay, SUM(l.dueDatePenaltyPerDay) AS totalDueDatePenaltyPerDay FROM Loan l GROUP BY l.lenderId", nativeQuery = true)
    List<Object[]> aggregateByLender();

    @Query(value = "SELECT l.interestPerDay, SUM(l.remainingAmount) AS totalRemainingAmount, GROUP_CONCAT(l.lenderId) AS lenderIds, GROUP_CONCAT(l.customerId) AS customerIds FROM Loan l GROUP BY l.interestPerDay", nativeQuery = true)
    List<Object[]> aggregateByInterest();

    @Query(value = "SELECT l.customerId, SUM(l.remainingAmount) AS totalRemainingAmount, GROUP_CONCAT(l.lenderId) AS lenderIds, SUM(l.interestPerDay) AS totalInterestPerDay, SUM(l.dueDatePenaltyPerDay) AS totalDueDatePenaltyPerDay FROM Loan l GROUP BY l.customerId", nativeQuery = true)
    List<Object[]> aggregateByCustomerId();
}
