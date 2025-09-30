package com.movie.ShowTimeX.Model;

import jakarta.persistence.Entity;

@Entity
public class CardPayment extends PaymentMethod {

    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expiryDate;

    // No-args constructor (required by JPA)
    public CardPayment() {
    }

    // Parameterized constructor
    public CardPayment(String cardNumber, String cardHolderName, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    // Getters & Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Card: " + cardNumber);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " via Card: " + cardNumber);
    }
}
