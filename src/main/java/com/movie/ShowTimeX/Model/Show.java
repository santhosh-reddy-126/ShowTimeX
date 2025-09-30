package com.movie.ShowTimeX.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.movie.ShowTimeX.Utilities.SeatUtil;
import jakarta.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;


    private Time startTime;
    private int seatsBooked;



    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonBackReference(value="admin-shows")
    private Admin admin;



    @ManyToOne
    @JoinColumn(name = "screen_id")
    @JsonBackReference(value="screen-shows")
    private Screen screen;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value="show-booking")
    private List<Booking> bookings = new ArrayList<>();

    public Show() {
        seatsBooked = 0;
    }

    public Show(Movie movie, Time startTime) {
        this.movie = movie;
        this.startTime = startTime;
        this.seatsBooked = 0;
    }

    // Getters & Setters
    public Integer getShowId() { return showId; }
    public Movie getMovie() { return movie; }
    public Time getStartTime() { return startTime; }
    public int getSeatsBooked() { return seatsBooked; }
    public List<Booking> getBookings() { return bookings; }
    public Admin getAdmin() {
        return admin;
    }
    public Screen getScreen() { return screen;}


    public void setMovie(Movie movie) { this.movie = movie; }
    public void setAdmin(Admin admin) { this.admin = admin; }
    public void setScreen(Screen screen) { this.screen = screen; }
    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }
    public void setStartTime(Time startTime) { this.startTime = startTime; }


    // convenience helper for bidirectional sync
    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setShow(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.setShow(null);
    }

    public void incrementSeats(int x){
        seatsBooked+=x;
    }
    public void decrementSeats(int x){
        seatsBooked-=x;
    }


    public boolean isSeatBooked(int seatNumber, int seatsPerRow) {
        for (Booking booking : bookings) {
            for (Seat seat : booking.getSeats()) {
                int bookedSeatNo = SeatUtil.getSeatNo(seat.getRowNumber(), seat.getColNumber(), seatsPerRow);
                if (bookedSeatNo == seatNumber) {
                    return true;
                }
            }
        }
        return false;
    }

}
