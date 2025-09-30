package com.movie.ShowTimeX.Controller;


import com.movie.ShowTimeX.DTO.*;
import com.movie.ShowTimeX.Model.Booking;
import com.movie.ShowTimeX.Service.UserBookingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/booking")
public class UserBookingController {

    private UserBookingService userBookingService;

    @Autowired
    public UserBookingController(UserBookingService userBookingService){
        this.userBookingService=userBookingService;
    }

    // Get All Bookings
    @GetMapping("/user/{userId}")
    public Map<String,List<Booking>> getAllBookings(@PathVariable Integer userId){
        List<Booking> allBookings = userBookingService.getAllBookingForUser(userId);
        Map<String,List<Booking>> booking = new HashMap<>();
        booking.put("data",allBookings);
        return booking;
    }

    // Get Bookings by a ID
    @GetMapping("/{bookingId}")
    public ResponseEntity<?> getBookingByID(@PathVariable Integer bookingId){
        Optional<Booking> myBooking = userBookingService.getBookingById(bookingId);

        if(myBooking.isPresent()){
            Map<String,Booking> booking = new HashMap<>();
            booking.put("data",myBooking.get());
            return ResponseEntity.ok(booking);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("message", "No Booking found")
            );
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addBooking(@RequestBody BookingRequest bookingRequest){
        if (bookingRequest.getUserId() == null || bookingRequest.getShowId() == null || bookingRequest.getSeatNo() == null) {
            return ResponseEntity.badRequest().body(Map.of("BookingStatus", false, "message", "Invalid request data"));
        }

        Response response = userBookingService.createBooking(
                bookingRequest.getShowId(),
                bookingRequest.getUserId(),
                bookingRequest.getSeatNo()
        );

        return ResponseEntity.status(response.isStatus() ? HttpStatus.CREATED : HttpStatus.CONFLICT)
                .body(Map.of("message", response.getMessage()));
    }


    //Cancel a Booking
    @DeleteMapping("")
    public ResponseEntity<?> cancelBooking(@RequestBody CancelBookingRequest cancelBookingRequest) {
        Response response = userBookingService.cancelBooking(cancelBookingRequest.getBookingId(),cancelBookingRequest.getUserId(),cancelBookingRequest.getSeatNo());
        return response.isStatus() ? ResponseEntity.ok(response):ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


}
