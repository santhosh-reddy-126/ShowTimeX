package com.movie.ShowTimeX.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer screenId;
    private  Integer screenNo;
    private  int seatsPerRow;
    private  int seatsPerCol;


    @ManyToOne
    @JoinColumn(name="theatre_id")
    @JsonBackReference(value = "theatre-screens")
    private Theatre theatre;

    @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL)
    @JsonManagedReference(value="screen-shows")
    private List<Show> shows;


    public Screen(){shows=new ArrayList<>();};

    public Screen(Integer screenNo, int seatsPerRow, int seatsPerCol) {
        this.screenNo = screenNo;
        this.seatsPerRow = seatsPerRow;
        this.seatsPerCol = seatsPerCol;
        this.shows = new ArrayList<>();
    }
    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Integer getScreenId() { return screenId; }
    public Integer getScreenNo() { return screenNo; }
    public int getSeatsPerRow() { return seatsPerRow; }
    public int getSeatsPerCol() { return seatsPerCol; }



}
