package com.isizwemadalane.investmentsapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Objects;

@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private ProductType productType;
    private Double currentBalance;
    private List<Withdrawal> withdrawalList;

    public void addWithdrawal(Withdrawal withdrawal) {
        if (withdrawal == null) {
            throw new IllegalArgumentException("Withdrawal cannot be null");
        }
        if (withdrawal.getAmount() > currentBalance) {
            throw new IllegalArgumentException("Withdrawal amount exceeds current balance");
        }
        withdrawalList.add(withdrawal);
        currentBalance -= withdrawal.getAmount();
    }

    public enum ProductType {
        RETIREMENT,
        SAVINGS
    }

    public Product() {}
    public Product(ProductType productType, Double currentBalance, List<Withdrawal> withdrawalList) {
        this.productType = productType;
        this.currentBalance = currentBalance;
        this.withdrawalList = withdrawalList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Product))
            return false;

        Product product = (Product) obj;

        return Objects.equals(this.productId, product.productId) &&
                Objects.equals(this.productType, product.productType) &&
                Objects.equals(this.currentBalance, product.currentBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.productId, this.productType, this.currentBalance);
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + this.productId +
                ", productType='" + this.productType + '\'' +
                ", currentBalance='" + this.currentBalance +  '\'' + '}';
    }

    // Getters and Setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<Withdrawal> getWithdrawalList() {
        return withdrawalList;
    }

    public void setWithdrawalList(List<Withdrawal> withdrawalList) {
        this.withdrawalList = withdrawalList;
    }
}
