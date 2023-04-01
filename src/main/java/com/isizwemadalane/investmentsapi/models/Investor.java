package com.isizwemadalane.investmentsapi.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobileNumber;
    private String address;

    public Investor(String firstName,
                    String lastName,
                    String emailAddress,
                    String mobileNumber,
                    String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Investor))
            return false;

        Investor investor = (Investor) obj;

        return Objects.equals(this.id, investor.id) &&
                Objects.equals(this.firstName, investor.firstName) &&
                Objects.equals(this.lastName, investor.lastName) &&
                Objects.equals(this.emailAddress, investor.emailAddress) &&
                Objects.equals(this.mobileNumber, investor.mobileNumber) &&
                Objects.equals(this.address, investor.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.id,
                this.firstName,
                this.lastName,
                this.emailAddress,
                this.mobileNumber,
                this.address
        );
    }

    @Override
    public String toString() {
        return "Investor{" + "id=" + this.id +
                ", firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", email='" + this.emailAddress + '\'' +
                ", phoneNumber='" + this.mobileNumber + '\'' +
                ", address='" + this.address +  '\'' + '}';
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
