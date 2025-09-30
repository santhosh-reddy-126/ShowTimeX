package com.movie.ShowTimeX.Service;


import com.movie.ShowTimeX.Model.Movie;
import com.movie.ShowTimeX.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserMovieService {
    private MovieRepository movieRepository;

    @Autowired
    public UserMovieService(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }

    // Get All Movies
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    // Get Movie by name
    public List<Movie> getMovieByName(String movieName){
        return movieRepository.findAll().stream().filter((movie -> movie.getMovieName().toLowerCase().startsWith(movieName.toLowerCase()))).toList();
    }


}
