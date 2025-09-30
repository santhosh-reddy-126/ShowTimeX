package com.movie.ShowTimeX.Controller;

import com.movie.ShowTimeX.DTO.Response;
import com.movie.ShowTimeX.Model.Admin;
import com.movie.ShowTimeX.Model.Movie;
import com.movie.ShowTimeX.Model.Screen;
import com.movie.ShowTimeX.Service.AdminScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/screens")
public class AdminScreenController {

    private AdminScreenService adminScreenService;

    @Autowired
    public AdminScreenController(AdminScreenService adminScreenService) {
        this.adminScreenService = adminScreenService;
    }

    // ----- Get all screens -----
    @GetMapping("/{theatreId}")
    public Map<String, List<Screen>> getAllScreens(@PathVariable Integer theatreId) {
        Map<String, List<Screen>> response = new HashMap<>();
        response.put("data", adminScreenService.getAllScreens(theatreId));
        return response;
    }

    // ----- Get a screen by ID -----
    @GetMapping("/{theatreId}/{screenId}")
    public ResponseEntity<?> getScreen(@PathVariable Integer theatreId,@PathVariable Integer screenId) {
        Optional<Screen> foundScreen = adminScreenService.getScreen(theatreId,screenId);
        return foundScreen.isPresent() ? ResponseEntity.ok(foundScreen.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Screen not found"));

    }

    // ----- Add a screen to a Theatre -----
    @PostMapping("/{theatreId}")
    public ResponseEntity<?> addScreen(@PathVariable Integer theatreId, @RequestBody Screen screen) {
        Response response = adminScreenService.addScreen(theatreId, screen);
        return response.isStatus() ? ResponseEntity.status(HttpStatus.CREATED).body(response):ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Theatre not found"));

    }

    // ----- Update a screen -----
    @PutMapping("/{theatreId}")
    public ResponseEntity<?> updateScreen(@PathVariable Integer theatreId,@RequestBody Screen screen) {
        Response response = adminScreenService.updateScreen(theatreId,screen);
        return  response.isStatus() ? ResponseEntity.status(HttpStatus.CREATED).body(response):ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // ----- Delete a screen -----
    @DeleteMapping("/{theatreId}/{screenId}")
    public ResponseEntity<?> deleteScreen(@PathVariable Integer theatreId,@PathVariable Integer screenId) {
        Response response = adminScreenService.deleteScreen(theatreId,screenId);
        return  response.isStatus() ? ResponseEntity.status(HttpStatus.CREATED).body(response):ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
