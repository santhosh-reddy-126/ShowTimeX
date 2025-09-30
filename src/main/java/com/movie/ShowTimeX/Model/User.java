package com.movie.ShowTimeX.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User extends Person {
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value="user-bookings")
    private List<Booking> userBookings;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private PaymentMethod paymentMethod;


    public User(String personName,String username,String password){
        super(personName,username,password);
        this.userBookings = new ArrayList<>();
        this.paymentMethod = null;
    }

    public User(){

    }



    public List<Booking> getUserBookings(){
        return userBookings;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setUserBookings(List<Booking> userBookings){
        this.userBookings = userBookings;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    public void pay(double amount){
        paymentMethod.pay(amount);
    }

}


