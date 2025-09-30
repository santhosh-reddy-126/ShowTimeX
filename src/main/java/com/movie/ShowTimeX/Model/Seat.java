package com.movie.ShowTimeX.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int rowNumber;
    private int colNumber;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    @JsonBackReference(value = "booking-seat")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name="show_id")
    @JsonBackReference("seat-show")
    private Show show;

    public Seat() {}

    public Seat(int rowNumber, int colNumber, Show show) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.show = show;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public int getRowNumber() { return rowNumber; }
    public int getColNumber() { return colNumber; }
    public Booking getBooking() { return booking; }
    public Show getShow() { return show; }

    public void setRowNumber(int rowNumber) { this.rowNumber = rowNumber; }
    public void setColNumber(int colNumber) { this.colNumber = colNumber; }
    public void setBooking(Booking booking) { this.booking = booking; }
    public void setShow(Show show) { this.show = show; }


}
