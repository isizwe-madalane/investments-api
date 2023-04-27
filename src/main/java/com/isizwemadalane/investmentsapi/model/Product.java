package com.isizwemadalane.investmentsapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_current_balance")
    private Double productCurrentBalance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "investor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("investor_id")
    private Investor investor;

    @OneToOne(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Withdrawal withdrawal;

    public Product() { super(); }
    public Product(ProductType productType, String productName, Double productCurrentBalance, Investor investor, Withdrawal withdrawal) {
        this.productType = productType;
        this.productName = productName;
        this.productCurrentBalance = productCurrentBalance;
        this.investor = investor;
        this.withdrawal = withdrawal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductCurrentBalance() {
        return productCurrentBalance;
    }

    public void setProductCurrentBalance(Double productCurrentBalance) {
        this.productCurrentBalance = productCurrentBalance;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Withdrawal getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Withdrawal withdrawal) {
        this.withdrawal = withdrawal;
    }
}
