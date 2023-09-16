package com.example.loanstore.scheduler;

import com.example.loanstore.model.Loan;
import com.example.loanstore.repository.LoanRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Log4j2
@Component
public class LoanScheduler implements CommandLineRunner {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public void run(String... args) throws Exception {
        // Add loans
        Loan loan1 = new Loan("L1", "C1", "LEN1", 10000, 10000, LocalDate.of(2023, 6, 5), 1, LocalDate.of(2023, 7, 5), 0.01);
        Loan loan2 = new Loan("L2", "C1", "LEN1", 20000, 5000, LocalDate.of(2023, 6, 1), 1, LocalDate.of(2023, 8, 5), 0.01);
        Loan loan3 = new Loan("L3", "C2", "LEN2", 50000, 30000, LocalDate.of(2023, 4, 4), 2, LocalDate.of(2023, 5, 4), 0.02);
        Loan loan4 = new Loan("L4", "C3", "LEN2", 50000, 30000, LocalDate.of(2023, 4, 4), 2, LocalDate.of(2023, 5, 4), 0.02);

        loanRepository.save(loan1);
        loanRepository.save(loan2);
        loanRepository.save(loan3);
        loanRepository.save(loan4);
    }

    @Scheduled(cron = "* * * * * *")
    public void checkDueDateAlert() {
        LocalDate currentDate = LocalDate.now();

        loanRepository.findAll()
        .stream()
        .filter(loan -> currentDate.isAfter(loan.getDueDate()))
        .forEach(loan -> log.error("Alert: Loan with ID " + loan.getLoanId() + " has crossed the due date."));

    }
}

