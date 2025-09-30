package com.movie.ShowTimeX.DTO;

import com.movie.ShowTimeX.Model.CardPayment;

import java.util.List;

public class CancelBookingRequest {

    private Integer bookingId;
    private Integer userId;
    private List<Integer> seatNo;




    public CancelBookingRequest() {}

    public CancelBookingRequest(Integer bookingId, Integer userId, List<Integer> seatNo) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.seatNo = seatNo;
    }

    public Integer getBookingId() { return bookingId; }
    public void setBookingId(Integer bookingId) { this.bookingId = bookingId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public List<Integer> getSeatNo() { return seatNo; }
    public void setSeatNo(List<Integer> seatNo) { this.seatNo = seatNo; }
}


