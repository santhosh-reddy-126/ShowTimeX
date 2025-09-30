package com.movie.ShowTimeX.Controller;


import com.movie.ShowTimeX.DTO.Response;
import com.movie.ShowTimeX.Model.Movie;
import com.movie.ShowTimeX.Model.Theatre;
import com.movie.ShowTimeX.Service.AdminMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class AdminMovieController {

    private AdminMovieService adminMovieService;

    @Autowired
    public AdminMovieController(AdminMovieService adminMovieService){
        this.adminMovieService=adminMovieService;
    }


    //Get all movies
    @GetMapping("")
    public Map<String, List<Movie>> getAllMovies(){
        Map<String, List<Movie>> response = new HashMap<>();
        response.put("data", adminMovieService.getAllMovies());
        return response;
    }

    //Get a movie by ID
    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovie(@PathVariable Integer movieId) {
        Optional<Movie> foundMovie = adminMovieService.getMovie(movieId);
        return foundMovie.isPresent() ? ResponseEntity.ok(Map.of("data", foundMovie.get())):ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Movie not found"));

    }


    //Add a movie
    @PostMapping("")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie){
        Response response = adminMovieService.addMovie(movie);
        return response.isStatus() ? ResponseEntity.status(HttpStatus.CREATED).body(response):ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }


    //Update a movie
    @PutMapping("")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
        Response response = adminMovieService.updateMovie(movie);
        return response.isStatus() ? ResponseEntity.status(HttpStatus.CREATED).body(response):ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    //Delete a Movie
    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer movieId) {
        Response response = adminMovieService.deleteMovie(movieId);
        return response.isStatus() ? ResponseEntity.status(HttpStatus.CREATED).body(response):ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }







}
