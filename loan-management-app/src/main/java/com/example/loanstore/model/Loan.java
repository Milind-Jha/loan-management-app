package com.example.loanstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Loan")
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    String loanId;

    @Column
    String customerId;

    @Column
    String lenderId;

    @Column
    Integer amount;

    @Column
    Integer remainingAmount;

    @Column
    LocalDate paymentDate;

    @Column
    int interestRatePerDay;

    @Column
    LocalDate dueDate;

    @Column
    double penaltyRatePerDay;

    @Column
    Boolean cancel;

    public Loan(String loanId, String customerId, String lenderId, Integer amount, Integer remainingAmount, LocalDate paymentDate, int interestRatePerDay, LocalDate dueDate, double penaltyRatePerDay) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.lenderId = lenderId;
        this.amount = amount;
        this.remainingAmount = remainingAmount;
        this.paymentDate = paymentDate;
        this.interestRatePerDay = interestRatePerDay;
        this.dueDate = dueDate;
        this.penaltyRatePerDay = penaltyRatePerDay;
    }
}
