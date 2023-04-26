package com.isizwemadalane.investmentsapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "withdrawal")
public class Withdrawal {

    @Id
    @Column(name = "withdrawal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "withdrawal_amount")
    private Double withdrawalAmount;

    @Column(name = "withdrawal_status")
    @Enumerated(EnumType.ORDINAL)
    private WithdrawalStatus withdrawalStatus;

    @OneToOne(mappedBy = "withdrawal")
    private Product product;

    public Withdrawal() {}
    public Withdrawal(Double withdrawalAmount, WithdrawalStatus withdrawalStatus, Product product) {
        super();
        this.withdrawalAmount = withdrawalAmount;
        this.withdrawalStatus = withdrawalStatus;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(Double withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public WithdrawalStatus getWithdrawalStatus() {
        return withdrawalStatus;
    }

    public void setWithdrawalStatus(WithdrawalStatus withdrawalStatus) {
        this.withdrawalStatus = withdrawalStatus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}