package com.movie.ShowTimeX.Controller;


import com.movie.ShowTimeX.Model.Movie;
import com.movie.ShowTimeX.Service.UserMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/movies")
public class UserMovieController {
    private UserMovieService userMovieService;

    @Autowired
    public UserMovieController(UserMovieService userMovieService){
        this.userMovieService=userMovieService;
    }

    //Get All Movies
    @GetMapping("")
    public Map<String, List<Movie>> getAllMovies() {
        Map<String, List<Movie>> response = new HashMap<>();
        response.put("data", userMovieService.getAllMovies());
        return response;
    }


    //Get a movie by Name
    @GetMapping("/{movieName}")
    public ResponseEntity<?> getMovie(@PathVariable String movieName) {
        List<Movie> filteredMovies = userMovieService.getMovieByName(movieName);

        if (filteredMovies.size()>0) {
            Map<String, List<Movie>> response = new HashMap<>();
            response.put("data",filteredMovies);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("message", "No Movies found")
            );
        }
    }
}
