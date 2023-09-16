package com.example.loanstore.controller;

import com.example.loanstore.model.Loan;
import com.example.loanstore.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping("/addLoan")
    void addLoan(@RequestBody Loan loan) throws Exception {
        loanService.addLoan(loan);
    }

    @GetMapping("/aggregateByLender")
    List<Object[]> aggregateByLender(){
        return loanService.aggregateByLender();
    }

    @GetMapping("/aggregateByInterest")
    List<Object[]> aggregateByInterest(){
        return loanService.aggregateByInterest();
    }

    @GetMapping("/aggregateByCustomerId")
    List<Object[]> aggregateByCustomerId(){
        return loanService.aggregateByCustomerId();
    }
}
