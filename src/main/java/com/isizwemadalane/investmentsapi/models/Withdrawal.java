package com.isizwemadalane.investmentsapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "WITHDRAWALS")
public class Withdrawal {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "INVESTOR_ID")
    private Long investorID;

    @Column(name = "AMOUNT")
    private Amount amount;

    protected Withdrawal() { }
    public Withdrawal(Long id, Amount amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvestorID() {
        return investorID;
    }

    public void setInvestorID(Long investorID) {
        this.investorID = investorID;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
