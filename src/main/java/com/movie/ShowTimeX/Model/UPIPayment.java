package com.movie.ShowTimeX.Model;

import jakarta.persistence.Entity;

@Entity
public class UPIPayment extends PaymentMethod {

    private String mobileNumber;
    private String username;

    // No-args constructor (required by JPA)
    public UPIPayment() {
    }

    // Parameterized constructor
    public UPIPayment(String mobileNumber, String username) {
        this.mobileNumber = mobileNumber;
        this.username = username;
    }

    // Getters & Setters
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI by user: " + username);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " via UPI to user: " + username);
    }
}
