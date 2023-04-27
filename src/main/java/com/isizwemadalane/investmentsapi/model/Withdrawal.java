package com.isizwemadalane.investmentsapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "withdrawals")
public class Withdrawal {

    @Id
    @Column(name = "withdrawal_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "withdrawal_amount")
    private Double withdrawalAmount;

    @Column(name = "withdrawal_status")
    @Enumerated(EnumType.STRING)
    private WithdrawalStatus withdrawalStatus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("product_id")
    private Product product;

    public Withdrawal() { super(); }
    public Withdrawal(Double withdrawalAmount, WithdrawalStatus withdrawalStatus, Product product) {
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