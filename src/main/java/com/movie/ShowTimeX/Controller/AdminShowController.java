package com.movie.ShowTimeX.Controller;

import com.movie.ShowTimeX.DTO.Response;
import com.movie.ShowTimeX.Model.Show;
import com.movie.ShowTimeX.Model.Theatre;
import com.movie.ShowTimeX.Service.AdminShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/shows")
public class AdminShowController {

    private final AdminShowService adminShowService;

    @Autowired
    public AdminShowController(AdminShowService adminShowService) {
        this.adminShowService = adminShowService;
    }

    // ----- Get all shows for a movie -----
    @GetMapping("/movie/{movieId}")
    public Map<String, List<Show>> getAllShows(@PathVariable Integer movieId) {
        Map<String, List<Show>> response = new HashMap<>();
        response.put("data", adminShowService.getAllShows(movieId));
        return response;
    }

    // ----- Get a show by ID -----
    @GetMapping("/{showId}")
    public ResponseEntity<?> getShow(@PathVariable Integer showId) {
        Optional<Show> showOpt = adminShowService.getShow(showId);
        return showOpt.isPresent() ? ResponseEntity.ok(showOpt.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Show not found"));
    }

    // ----- Add a show -----
    @PostMapping("")
    public ResponseEntity<?> addShow(@RequestBody Map<String, String> request) {
        int movieId = Integer.parseInt(request.get("movieId"));
        int screenId = Integer.parseInt(request.get("screenId"));
        int adminId = Integer.parseInt(request.get("adminId"));
        String startTime = request.get("startTime");

        Response response = adminShowService.addShow(movieId, screenId, adminId, startTime);

        return response.isStatus()
                ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // ----- Update a show -----
    @PutMapping("")
    public ResponseEntity<?> updateShow(@RequestBody Map<String, String> request) {
        int showId = Integer.parseInt(request.get("showId"));
        int movieId = Integer.parseInt(request.get("movieId"));
        int screenId = Integer.parseInt(request.get("screenId"));
        int adminId = Integer.parseInt(request.get("adminId"));
        String startTime = request.get("startTime");

        Response response = adminShowService.updateShow(showId, movieId, screenId, adminId, startTime);

        return response.isStatus()
                ? ResponseEntity.status(HttpStatus.OK).body(response)
                : ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // ----- Delete a show -----
    @DeleteMapping("/{showId}")
    public ResponseEntity<?> deleteShow(@PathVariable Integer showId) {
        Response response = adminShowService.deleteShow(showId);


        return response.isStatus()
                ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    // ----- Get Admin Shows -----
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<?> adminShows(@PathVariable Integer adminId) {
        List<Show> allShows = adminShowService.getShowsByAdmin(adminId);
        return ResponseEntity.ok(Map.of("data",allShows));
    }
}
