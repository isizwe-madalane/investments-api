package com.isizwemadalane.investmentsapi.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "INVESTORS")
public class Investor {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
    private Set<Product> products = new HashSet<Product>();

    protected Investor() {
    }

    public Investor(String name,
                    String surname,
                    LocalDate dateOfBirth,
                    String address,
                    String mobileNumber,
                    String emailAddress) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public Product getProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        throw new IllegalArgumentException("No such product  with the name '" + name + "'");
    }

    public Withdrawal makeWithdrawal(Amount amount) {
        for (Product product : products) {
            if (product.getName() == "RETIREMENT" && getAge() < 65) {
                throw new IllegalStateException("Cannot make a withdrawal, must be older than 65");
            }
            return new Withdrawal(getId(), amount);
        }
        throw new RuntimeException("Could not make a withdrawal");
    }

    @Override
    public String toString() {
        return "Investor{ id=" + this.id +
                ", name='" + this.name + '\'' +
                ", surname='" + this.surname + '\'' +
                ", dateOfBirth='" + this.dateOfBirth + '\'' +
                ", address='" + this.address + '\'' +
                ", mobileNumber='" + this.mobileNumber + '\'' +
                ", emailAddress='" + this.emailAddress + '\'';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Investor)) return false;
        Investor investor = (Investor) obj;
        return Objects.equals(this.id, investor.id) &&
                Objects.equals(this.name, investor.name) &&
                Objects.equals(this.surname, investor.surname) &&
                Objects.equals(this.dateOfBirth, investor.dateOfBirth) &&
                Objects.equals(this.address, investor.address) &&
                Objects.equals(this.mobileNumber, investor.mobileNumber) &&
                Objects.equals(this.emailAddress, investor.emailAddress);
    }

    private int getAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period.getYears();
    }
}
