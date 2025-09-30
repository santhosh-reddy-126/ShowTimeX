package com.movie.ShowTimeX.Controller;

import com.movie.ShowTimeX.DTO.Response;
import com.movie.ShowTimeX.Model.Movie;
import com.movie.ShowTimeX.Model.Theatre;
import com.movie.ShowTimeX.Service.AdminTheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/theatres")
public class AdminTheatreController {
    private AdminTheatreService adminTheatreService;

    @Autowired
    public AdminTheatreController(AdminTheatreService adminTheatreService){
        this.adminTheatreService=adminTheatreService;
    }


    //Get all theatres
    @GetMapping("")
    public Map<String, List<Theatre>> getAllTheatres(){
        Map<String, List<Theatre>> response = new HashMap<>();
        response.put("data", adminTheatreService.getAllTheatres());
        return response;
    }

    //Get a theatre by ID
    @GetMapping("/{theatreId}")
    public ResponseEntity<?> getTheatre(@PathVariable Integer theatreId) {
        Optional<Theatre> foundTheatre = adminTheatreService.getTheatre(theatreId);
        return foundTheatre.isPresent() ? ResponseEntity.ok(Map.of("message", foundTheatre.get())) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Movie not found"));

    }


    //Add a theatre
    @PostMapping("")
    public ResponseEntity<?> addMovie(@RequestBody Theatre theatre){
        Response response = adminTheatreService.addTheatre(theatre);
        return response.isStatus() ? ResponseEntity.status(HttpStatus.CREATED).body(response):ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    //Update a theatre
    @PutMapping("")
    public ResponseEntity<?> updateTheatre(@RequestBody Theatre theatre){
        Response response = adminTheatreService.updateTheatre(theatre);
        return response.isStatus() ? ResponseEntity.status(HttpStatus.CREATED).body(response):ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    //Delete a Theatre
    @DeleteMapping("/{theatreId}")
    public ResponseEntity<?> deleteTheatre(@PathVariable Integer theatreId) {
        Response response = adminTheatreService.deleteTheatre(theatreId);
        return response.isStatus() ? ResponseEntity.status(HttpStatus.CREATED).body(response):ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
