package com.movie.ShowTimeX.Controller;


import com.movie.ShowTimeX.DTO.Ticket;
import com.movie.ShowTimeX.Model.Movie;
import com.movie.ShowTimeX.Model.Show;
import com.movie.ShowTimeX.Service.UserShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/show")
public class UserShowController {
    private UserShowService userShowService;

    @Autowired
    public UserShowController(UserShowService userShowService){
        this.userShowService=userShowService;
    }

    //Get All shows of movie by ID
    @GetMapping("/{movieId}")
    public ResponseEntity<?> getShowsByMovieID(@PathVariable Integer movieId){
        List<Show> movieShows = userShowService.findShowByMovieID(movieId);
        List<Ticket> tickets = new ArrayList<>();
        for(Show currShow:movieShows){
            tickets.add(new Ticket(currShow,currShow.getScreen()));
        }

        if (movieShows.size()>0) {
            Map<String, List<Ticket>> response = new HashMap<>();
            response.put("data",tickets);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("message", "No Movies found")
            );
        }
    }
}
