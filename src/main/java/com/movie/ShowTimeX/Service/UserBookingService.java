package com.movie.ShowTimeX.Service;

import com.movie.ShowTimeX.DTO.Response;
import com.movie.ShowTimeX.Model.*;
import com.movie.ShowTimeX.Repository.BookingRepository;
import com.movie.ShowTimeX.Repository.PersonRepository;
import com.movie.ShowTimeX.Utilities.SeatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserBookingService {

    private final BookingRepository bookingRepository;
    private final UserShowService userShowService;
    private final PersonService personService;
    private final PersonRepository personRepository;

    @Autowired
    public UserBookingService(BookingRepository bookingRepository, UserShowService userShowService,
                              PersonService personService, PersonRepository personRepository) {
        this.bookingRepository = bookingRepository;
        this.userShowService = userShowService;
        this.personService = personService;
        this.personRepository = personRepository;
    }

    // Get All Bookings for a user
    public List<Booking> getAllBookingForUser(Integer userId) {
        return bookingRepository.findByUser_PersonId(userId);
    }

    // Get Booking by ID
    public Optional<Booking> getBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId);
    }



    public Response createBooking(Integer showId, Integer userId, List<Integer> seatNo) {
        try {
            if (seatNo == null || seatNo.isEmpty()){
                return new Response(false,"Please Select Valid Seats");
            }

            Optional<User> userOpt = personService.getUserById(userId);
            Optional<Show> showOpt = userShowService.findShowByID(showId);

            if (userOpt.isEmpty() || showOpt.isEmpty()) return new Response(false,"Please Select Valid Movie and Show");

            User user = userOpt.get();
            Show show = showOpt.get();
            Screen screen = show.getScreen();

            List<Seat> bookedSeats = new ArrayList<>();

            for (Integer seat : seatNo) {
                int row = SeatUtil.getRow(seat, screen.getSeatsPerRow());
                int col = SeatUtil.getCol(seat, screen.getSeatsPerRow());
                int seatNumber = SeatUtil.getSeatNo(row, col, screen.getSeatsPerRow());

                if (show.isSeatBooked(seatNumber, screen.getSeatsPerRow())) {
                    System.out.println("Seat " + seatNumber + " is already booked.");
                    return new Response(false,"Cannot confirm already Booked Seats!");
                }

                bookedSeats.add(new Seat(row, col, show));
            }

            Booking booking = new Booking(user, show, bookedSeats);
            show.incrementSeats(seatNo.size());

            // Save Booking
            bookingRepository.save(booking);

            PaymentMethod payment = user.getPaymentMethod();
            if(payment==null){
                return new Response(false, "No payment method found for this user.");
            }
            payment.pay(seatNo.size() * show.getMovie().getTicketPrice());
            personRepository.save(user);

            return new Response(true,"Booking Successful");

        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false,e.getMessage());
        }
    }



    public Response cancelBooking(Integer bookingId, Integer userId, List<Integer> seatNo) {
        try {
            if (seatNo == null || seatNo.isEmpty())
                return new Response(false,"Please Select Valid Seats");

            Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
            Optional<User> userOpt = personService.getUserById(userId);

            if (bookingOpt.isEmpty() || userOpt.isEmpty())
                return new Response(false,"Please Select Valid Booking");

            Booking booking = bookingOpt.get();
            User user = userOpt.get();

            if (!booking.getUser().getPersonId().equals(user.getPersonId())) {
                return new Response(false,"Cannot cancel booking: not your booking.");
            }

            Screen screen = booking.getShow().getScreen();
            int rowSeats = screen.getSeatsPerRow();


            List<Seat> bookedSeats = booking.getSeats();
            Set<Integer> bookedSeatNos = bookedSeats.stream()
                    .map(seat -> SeatUtil.getSeatNo(seat.getRowNumber(), seat.getColNumber(), rowSeats))
                    .collect(Collectors.toSet());

            // Validate requested seats
            for (Integer seat : seatNo) {
                if (!bookedSeatNos.contains(seat)) {
                    return new Response(false, "Seat " + seat + " is not booked in this booking.");
                }
            }


            booking.getSeats().removeIf(seat ->
                    seatNo.contains(SeatUtil.getSeatNo(seat.getRowNumber(), seat.getColNumber(), rowSeats))
            );

            // Update show seat count
            booking.getShow().decrementSeats(seatNo.size());

            // Refund
            PaymentMethod payment = user.getPaymentMethod();
            if (payment == null) {
                return new Response(false, "No payment method found for this user.");
            }
            payment.refund(seatNo.size() * booking.getShow().getMovie().getTicketPrice());

            // Save or delete booking
            if (booking.getSeats().isEmpty()) {
                bookingRepository.delete(booking); // delete entity, not byId
            } else {
                bookingRepository.save(booking);
            }

            return new Response(true,"Cancellation Successful");

        } catch (Exception e) {
            e.printStackTrace();
            return new Response(false,e.getMessage());
        }
    }

}
