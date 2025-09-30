package com.movie.ShowTimeX.Repository;


import com.movie.ShowTimeX.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    List<Booking> findByUser_PersonId(Integer userId);
}
