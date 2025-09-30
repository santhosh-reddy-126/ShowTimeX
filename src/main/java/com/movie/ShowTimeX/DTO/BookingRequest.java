package com.movie.ShowTimeX.DTO;


import com.movie.ShowTimeX.Model.CardPayment;
import com.movie.ShowTimeX.Model.PaymentMethod;
import com.movie.ShowTimeX.Model.UPIPayment;

import java.util.List;

public class BookingRequest {
    private Integer showId;
    private Integer userId;
    private List<Integer> seatNo;

    public BookingRequest(Integer showId, Integer userId, List<Integer> seatNo) {
        this.showId = showId;
        this.userId = userId;
        this.seatNo = seatNo;
    }

    public BookingRequest(){};

    // Getters & Setters
    public Integer getShowId() { return showId; }
    public void setShowId(Integer showId) { this.showId = showId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public List<Integer> getSeatNo() { return seatNo; }
    public void setSeatNo(List<Integer> seatNo) { this.seatNo = seatNo; }

}
