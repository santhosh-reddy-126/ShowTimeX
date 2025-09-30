package com.movie.ShowTimeX.Model;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @OneToOne(mappedBy = "paymentMethod")
    private User user;


    abstract public void pay(double amount);

    abstract public void refund(double amount);
}
