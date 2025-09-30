package com.movie.ShowTimeX.Service;

import com.movie.ShowTimeX.DTO.Response;
import com.movie.ShowTimeX.Model.Theatre;
import com.movie.ShowTimeX.Repository.MovieRepository;
import com.movie.ShowTimeX.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdminTheatreService {

    private TheatreRepository theatreRepository;

    @Autowired
    public AdminTheatreService(TheatreRepository theatreRepository){
        this.theatreRepository=theatreRepository;
    }


    //Get all theatres
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    //Add a theatre
    public Response addTheatre(Theatre theatre) {
        try{
            theatreRepository.save(theatre);
            return new Response(true,"Theatre updated successfully!");
        }catch(Exception e){
            return new Response(false,e.getMessage());
        }

    }

    //Update a theatre
    public Response updateTheatre(Theatre theatre) {
        try{
            theatreRepository.save(theatre);
            return new Response(true,"Theatre updated successfully!");
        }catch(Exception e){
            return new Response(false,e.getMessage());
        }
    }

    //Delete a theatre
    public Response deleteTheatre(Integer theatreId){
        Optional<Theatre> currTheatre = theatreRepository.findById(theatreId);
        if(currTheatre.isPresent()){
            theatreRepository.delete(currTheatre.get());
            return new Response(true,"Theatre deleted successfully!");
        }else{
            return new Response(false,"Theatre not found!");
        }
    }

    //Get Theatre by Id
    public Optional<Theatre> getTheatre(Integer theatreId) {
        return theatreRepository.findById(theatreId);
    }
}
