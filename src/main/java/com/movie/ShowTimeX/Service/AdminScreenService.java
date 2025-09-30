package com.movie.ShowTimeX.Service;

import com.movie.ShowTimeX.DTO.Response;
import com.movie.ShowTimeX.Model.Movie;
import com.movie.ShowTimeX.Model.Screen;
import com.movie.ShowTimeX.Model.Theatre;
import com.movie.ShowTimeX.Repository.ScreenRepository;
import com.movie.ShowTimeX.Repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdminScreenService {

    private ScreenRepository screenRepository;
    private TheatreRepository theatreRepository;
    public AdminScreenService(ScreenRepository screenRepository,TheatreRepository theatreRepository){
        this.screenRepository=screenRepository;
        this.theatreRepository=theatreRepository;
    }


    //Get all screen for a theatre
    public List<Screen> getAllScreens(Integer theatreId) {
        Optional<Theatre> currTheatre = theatreRepository.findById(theatreId);
        if(currTheatre.isPresent()){
            return currTheatre.get().getTheatreScreens();
        }else{
            return new ArrayList<>();
        }
    }

    //Get Screen by Id
    public Optional<Screen> getScreen(Integer theatreId,Integer screenId) {
        List<Screen> myScreens = getAllScreens(theatreId);
        return myScreens.stream().filter((screen -> screen.getScreenId().equals(screenId))).findFirst();
    }

    //Add a Screen
    public Response addScreen(Integer theatreId,Screen screen){
        try {
            Optional<Theatre> theatreOpt = theatreRepository.findById(theatreId);
            if (theatreOpt.isEmpty()) {
                return new Response(false,"No Theatre found!");
            }

            Theatre theatre = theatreOpt.get();
            screen.setTheatre(theatre);
            theatre.addScreen(screen);
            screenRepository.save(screen);
            return new Response(true,"Screen added successfully!");
        } catch (Exception e) {
            return new Response(false,e.getMessage());
        }
    }


    //Update a screen
    public Response updateScreen(Integer theatreId,Screen screen) {
        try{
            if (screen.getScreenId() == null) return new Response(false,"No Screen found!");
            Theatre theatre = theatreRepository.findById(theatreId).orElseThrow();
            screen.setTheatre(theatre);
            screenRepository.save(screen);
            return new Response(true,"Screen updated successfully!");
        }catch(Exception e){
            return new Response(false,e.getMessage());
        }
    }

    //Delete a screen
    public Response deleteScreen(Integer theatreId, Integer screenId) {
        try {
            Optional<Screen> screenOpt = screenRepository.findById(screenId);
            if (screenOpt.isEmpty()) return new Response(false,"No Screen found!");

            Screen screen = screenOpt.get();

            if (!screen.getTheatre().getTheatreId().equals(theatreId)) return new Response(false,"Screen doesn't belong to this theatre!");
            screenRepository.delete(screen);
            return new Response(true,"Screen deleted successfully!");
        } catch (Exception e) {
            return new Response(true,e.getMessage());
        }
    }



}
