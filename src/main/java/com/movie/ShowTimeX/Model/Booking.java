package com.movie.ShowTimeX.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    //User who is associated with this booking
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value="user-bookings")
    private User user;

    //Show associated with this booking
    @ManyToOne
    @JoinColumn(name = "show_id")
    @JsonBackReference(value="show-booking")
    private Show show;



    //Seats under this Booking
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "booking-seat")
    private List<Seat> seats = new ArrayList<>();

    public Booking() {}

    public Booking(User user, Show show, List<Seat> seats) {
        this.user = user;
        this.show = show;
        this.seats = seats;
        for (Seat seat : seats) {
            seat.setBooking(this);
        }
    }

    public Integer getBookingId() { return bookingId; }
    public User getUser() { return user; }
    public Show getShow() { return show; }
    public List<Seat> getSeats() { return seats; }

    public void setUser(User user) { this.user = user; }
    public void setShow(Show show) { this.show = show; }
    public void setSeats(List<Seat> seats) {
        this.seats = seats;
        for (Seat seat : seats) {
            seat.setBooking(this);
        }
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
        seat.setBooking(this);
    }

    public void removeSeat(Seat seat) {
        seats.remove(seat);
        seat.setBooking(null);
    }
}
