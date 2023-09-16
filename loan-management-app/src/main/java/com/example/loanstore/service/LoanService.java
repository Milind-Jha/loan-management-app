package com.example.loanstore.service;

import com.example.loanstore.model.Loan;
import com.example.loanstore.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    @Autowired
    LoanRepository loanRepository;

    public Loan addLoan(Loan loan) throws Exception {
        if (loan.getPaymentDate().isAfter(loan.getDueDate())) {
            throw new Exception("Payment date can't be greater than due date.");
        }
        return loanRepository.save(loan);
    }

    public List<Object[]> aggregateByLender() {
        return loanRepository.aggregateByLender();
    }

    public List<Object[]> aggregateByInterest() {
        return loanRepository.aggregateByInterest();
    }

    public List<Object[]> aggregateByCustomerId() {
        return loanRepository.aggregateByCustomerId();
    }
}
