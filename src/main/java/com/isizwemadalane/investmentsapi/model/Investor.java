package com.isizwemadalane.investmentsapi.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "investor")
public class Investor {

    @Id
    @Column(name = "investor_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "investor_name")
    private String investorName;

    @Column(name = "investor_surname")
    private String investorSurname;

    @Column(name = "investor_date_of_birth")
    private String investorDateOfBirth;

    @Column(name = "investor_address")
    private String investorAddress;

    @Column(name = "investor_mobile_number")
    private String investorMobileNumber;

    @Column(name = "investor_email_address")
    private String investorEmailAddress;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "investor", cascade = CascadeType.ALL)
    private Set<Product> products;

    public Investor() { super(); }
    public Investor(String investorName, String investorSurname, String investorDateOfBirth, String investorAddress, String investorMobileNumber, String investorEmailAddress, Set<Product> products) {
        super();

        this.investorName = investorName;
        this.investorSurname = investorSurname;
        this.investorDateOfBirth = investorDateOfBirth;
        this.investorAddress = investorAddress;
        this.investorMobileNumber = investorMobileNumber;
        this.investorEmailAddress = investorEmailAddress;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getInvestorSurname() {
        return investorSurname;
    }

    public void setInvestorSurname(String investorSurname) {
        this.investorSurname = investorSurname;
    }

    public String getInvestorDateOfBirth() {
        return investorDateOfBirth;
    }

    public void setInvestorDateOfBirth(String investorDateOfBirth) {
        this.investorDateOfBirth = investorDateOfBirth;
    }

    public String getInvestorAddress() {
        return investorAddress;
    }

    public void setInvestorAddress(String investorAddress) {
        this.investorAddress = investorAddress;
    }

    public String getInvestorMobileNumber() {
        return investorMobileNumber;
    }

    public void setInvestorMobileNumber(String investorMobileNumber) {
        this.investorMobileNumber = investorMobileNumber;
    }

    public String getInvestorEmailAddress() {
        return investorEmailAddress;
    }

    public void setInvestorEmailAddress(String investorEmailAddress) {
        this.investorEmailAddress = investorEmailAddress;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
