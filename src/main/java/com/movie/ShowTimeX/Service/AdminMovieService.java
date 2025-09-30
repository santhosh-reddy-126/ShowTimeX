package com.movie.ShowTimeX.Service;

import com.movie.ShowTimeX.DTO.Response;
import com.movie.ShowTimeX.Model.Movie;
import com.movie.ShowTimeX.Model.Theatre;
import com.movie.ShowTimeX.Repository.MovieRepository;
import com.movie.ShowTimeX.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdminMovieService {
    private MovieRepository movieRepository;

    @Autowired
    public AdminMovieService(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }


    //Get all movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    //Add a movie
    public Response addMovie(Movie movie) {
        try{
            movieRepository.save(movie);
            return new Response(true,"Movie added successfully!");
        }catch(Exception e){
            return new Response(false,e.getMessage());
        }

    }

    //Update a movie
    public Response updateMovie(Movie movie) {
        try{
            movieRepository.save(movie);
            return new Response(true,"Movie updated successfully!");
        }catch(Exception e){
            return new Response(false,e.getMessage());
        }
    }

    //Delete a movie
    public Response deleteMovie(Integer movieId){
        Optional<Movie> currMovie = movieRepository.findById(movieId);
        if(currMovie.isPresent()){
            movieRepository.delete(currMovie.get());
            return new Response(true,"Movie deleted successfully!");
        }else{
            return new Response(false,"Movie not found!");
        }
    }

    //Get Movie by Id
    public Optional<Movie> getMovie(Integer movieId) {
        return movieRepository.findById(movieId);
    }




}
