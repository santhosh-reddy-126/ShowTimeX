package com.movie.ShowTimeX.Service;


import com.movie.ShowTimeX.Model.Show;
import com.movie.ShowTimeX.Repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserShowService {

    private ShowRepository showRepository;

    @Autowired
    public UserShowService(ShowRepository showRepository){
        this.showRepository=showRepository;
    }


    public List<Show> findShowByMovieID(Integer movieId){
        return showRepository.findByMovie_MovieId(movieId);
    }

    public Optional<Show> findShowByID(Integer showId){
        return showRepository.findById(showId);
    }
}
